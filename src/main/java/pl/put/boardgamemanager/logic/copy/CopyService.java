package pl.put.boardgamemanager.logic.copy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.model.game.Game;
import pl.put.boardgamemanager.logic.game.GameRepository;
import pl.put.boardgamemanager.dto.GameWithCopiesSetDTO;
import pl.put.boardgamemanager.model.copy.Copy;
import pl.put.boardgamemanager.dto.CopyDTO;
import pl.put.boardgamemanager.dto.GameCopyNameDTO;
import pl.put.boardgamemanager.model.rental.private_rental.PrivateRental;
import pl.put.boardgamemanager.logic.rental.private_rental.PrivateRentalRepository;
import pl.put.boardgamemanager.model.rental.tournament_rental.TournamentRental;
import pl.put.boardgamemanager.logic.rental.tournament_rental.TournamentRentalRepository;
import pl.put.boardgamemanager.util.Utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CopyService {

    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PrivateRentalRepository privateRentalRepository;

    @Autowired
    private TournamentRentalRepository tournamentRentalRepository;

    private List<Copy> getTournamentRentalGameCopies() {
        return tournamentRentalRepository
                .findAll()
                .stream()
                .map(TournamentRental::getCopyId)
                .map(copyId -> copyRepository.findById(copyId).orElse(null))
                .collect(Collectors.toList());
    }

    private List<Copy> getBusyRentalCopies(Timestamp rentalTime, Integer duration) {
        PrivateRental desiredRental = new PrivateRental();
        desiredRental.setStartTime(rentalTime);
        desiredRental.setDuration(duration);

        return privateRentalRepository
                .findAll()
                .stream()
                .filter(rental -> Utils.isEventDuringAnother(rental, desiredRental))
                .map(PrivateRental::getCopyId)
                .map(copyId -> copyRepository.findById(copyId).orElse(null))
                .collect(Collectors.toList());
    }

    public CopyDTO get(Long id) {
        Copy copy = copyRepository.findById(id).orElse(null);
        if(copy == null) return null;
        else return copy.toDTO();
    }

    public Integer countGames(Long gameId) {
        return copyRepository.findAllByGameId(gameId).size();
    }

    public List<CopyDTO> all() {
        return copyRepository.findAll().stream()
                .map(Copy::toDTO)
                .collect(Collectors.toList());
    }

    public CopyDTO create(CopyDTO dto) {
        Copy copy = new Copy();
        copy.updateParamsFrom(dto);
        copyRepository.save(copy);
        return copy.toDTO();
    }

    private List<Copy> getAvailableGameCopiesFor(Timestamp startTime, Integer duration) {
        List<Copy> allCopies = copyRepository.findAll();
        allCopies.removeAll(getTournamentRentalGameCopies());
        allCopies.removeAll(getBusyRentalCopies(startTime, duration));

        return allCopies;
    }

    public List<GameWithCopiesSetDTO> getAvailableGameWithCopiesSetDTOs(Timestamp startTime, Integer duration) {
        List<Game> allGames = gameRepository.findAll();

        List<Copy> availableGameCopies = getAvailableGameCopiesFor(startTime, duration);

        return allGames
                .stream()
                .map(game -> {
                    List<Copy> copies = new ArrayList<>();
                    availableGameCopies.forEach(copy -> {
                        if(copy.getGameId().equals(game.getId())) copies.add(copy);
                    });

                    GameWithCopiesSetDTO dto = new GameWithCopiesSetDTO();
                    dto.setGame(game);
                    dto.setGameCopies(copies);

                    return dto;
                })
                .collect(Collectors.toList());

    }

    public List<CopyDTO> getAvailableGameCopyDTOsFor(Timestamp startTime, Integer duration) {
        return getAvailableGameCopiesFor(startTime, duration)
                .stream()
                .map(Copy::toDTO)
                .collect(Collectors.toList());
    }

    public List<GameCopyNameDTO> getAvailableGameCopyNameDTOsFor(Timestamp startTime, Integer duration) {
        return getAvailableGameCopiesFor(startTime, duration)
                .stream()
                .map(copy -> {
                    Game game = gameRepository.findById(copy.getGameId()).orElse(null);
                    if(game == null) return null;
                    else {
                        GameCopyNameDTO dto = new GameCopyNameDTO();
                        dto.setName(game.getName());
                        dto.setCopyId(copy.getId());
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


    public CopyDTO update(CopyDTO dto) {
        return copyRepository.findById(dto.getId())
                .map(existingCopy -> {
                    existingCopy.updateParamsFrom(dto);
                    copyRepository.save(existingCopy);
                    return existingCopy.toDTO();
                })
                .orElseGet(() -> create(dto));
    }

    public void delete(Long id) {
        copyRepository.deleteById(id);
    }
}
