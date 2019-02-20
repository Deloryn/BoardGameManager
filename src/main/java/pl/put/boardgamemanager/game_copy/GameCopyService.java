package pl.put.boardgamemanager.game_copy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.game.Game;
import pl.put.boardgamemanager.game.GameRepository;
import pl.put.boardgamemanager.rental.private_rental.PrivateRental;
import pl.put.boardgamemanager.rental.private_rental.PrivateRentalRepository;
import pl.put.boardgamemanager.rental.tournament_rental.TournamentRental;
import pl.put.boardgamemanager.rental.tournament_rental.TournamentRentalRepository;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class GameCopyService {

    @Autowired
    private GameCopyRepository gameCopyRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PrivateRentalRepository privateRentalRepository;

    @Autowired
    private TournamentRentalRepository tournamentRentalRepository;

    private boolean isRentalDuringAnother(PrivateRental rental, PrivateRental another) {
        if (rental.getRentalTime().before(another.getRentalTime()))
            return calculateFinishTime(rental).after(another.getRentalTime());
        else
            return rental.getRentalTime().before(calculateFinishTime(another));
    }

    private Timestamp addToTimestamp(Timestamp ts, Integer seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(ts.getTime());
        cal.add(Calendar.SECOND, seconds);
        return new Timestamp(cal.getTime().getTime());
    }

    private Timestamp calculateFinishTime(PrivateRental rental) {
        return addToTimestamp(rental.getRentalTime(), rental.getDuration() * 60);
    }

    private List<GameCopy> getTournamentRentalGameCopies() {
        return tournamentRentalRepository
                .findAll()
                .stream()
                .map(TournamentRental::getCopyId)
                .map(copyId -> gameCopyRepository.findById(copyId).orElse(null))
                .collect(Collectors.toList());
    }

    private List<GameCopy> getBusyRentalCopies(Timestamp rentalTime, Integer duration) {
        PrivateRental desiredRental = new PrivateRental();
        desiredRental.setRentalTime(rentalTime);
        desiredRental.setDuration(duration);

        return privateRentalRepository
                .findAll()
                .stream()
                .filter(rental -> isRentalDuringAnother(rental, desiredRental))
                .map(PrivateRental::getCopyId)
                .map(copyId -> gameCopyRepository.findById(copyId).orElse(null))
                .collect(Collectors.toList());
    }

    public GameCopyDTO get(Long id) {
        GameCopy gameCopy = gameCopyRepository.findById(id).orElse(null);
        if(gameCopy == null) return null;
        else return gameCopy.toDTO();
    }

    public Integer countGames(Long gameId) {
        return gameCopyRepository.findAllByGameId(gameId).size();
    }

    public List<GameCopyDTO> all() {
        return gameCopyRepository.findAll().stream()
                .map(GameCopy::toDTO)
                .collect(Collectors.toList());
    }

    public GameCopyDTO create(GameCopyDTO dto) {
        GameCopy gameCopy = new GameCopy();
        gameCopy.updateParamsFrom(dto);
        gameCopyRepository.save(gameCopy);
        return gameCopy.toDTO();
    }

    public List<GameCopyNameDTO> getAvailableGameCopiesFor(Timestamp startTime, Integer duration) {
        List<GameCopy> allCopies = gameCopyRepository.findAll();
        allCopies.removeAll(getTournamentRentalGameCopies());
        allCopies.removeAll(getBusyRentalCopies(startTime, duration));

        return allCopies
                .stream()
                .map(gameCopy -> {
                    Game game = gameRepository.findById(gameCopy.getGameId()).orElse(null);
                    if(game == null) return null;
                    else {
                        GameCopyNameDTO dto = new GameCopyNameDTO();
                        dto.setName(game.getName());
                        dto.setCopyId(gameCopy.getId());
                        return dto;
                    }
                })
                .filter(distinctByKey(GameCopyNameDTO::getName))
                .collect(Collectors.toList());

    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


    public GameCopyDTO update(GameCopyDTO dto) {
        return gameCopyRepository.findById(dto.getId())
                .map(existingCopy -> {
                    existingCopy.updateParamsFrom(dto);
                    gameCopyRepository.save(existingCopy);
                    return existingCopy.toDTO();
                })
                .orElseGet(() -> create(dto));
    }

    public void delete(Long id) {
        gameCopyRepository.deleteById(id);
    }
}
