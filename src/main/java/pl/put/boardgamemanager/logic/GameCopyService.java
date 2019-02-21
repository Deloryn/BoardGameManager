package pl.put.boardgamemanager.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.model.Game;
import pl.put.boardgamemanager.logic.repository.GameRepository;
import pl.put.boardgamemanager.dto.GameWithCopiesSetDTO;
import pl.put.boardgamemanager.model.GameCopy;
import pl.put.boardgamemanager.dto.GameCopyDTO;
import pl.put.boardgamemanager.dto.GameCopyNameDTO;
import pl.put.boardgamemanager.logic.repository.GameCopyRepository;
import pl.put.boardgamemanager.model.PrivateRental;
import pl.put.boardgamemanager.logic.repository.PrivateRentalRepository;
import pl.put.boardgamemanager.model.TournamentRental;
import pl.put.boardgamemanager.logic.repository.TournamentRentalRepository;
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
public class GameCopyService {

    @Autowired
    private GameCopyRepository gameCopyRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PrivateRentalRepository privateRentalRepository;

    @Autowired
    private TournamentRentalRepository tournamentRentalRepository;

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
                .filter(rental -> Utils.isEventDuringAnother(rental, desiredRental))
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

    private List<GameCopy> getAvailableGameCopiesFor(Timestamp startTime, Integer duration) {
        List<GameCopy> allCopies = gameCopyRepository.findAll();
        allCopies.removeAll(getTournamentRentalGameCopies());
        allCopies.removeAll(getBusyRentalCopies(startTime, duration));

        return allCopies;
    }

    public List<GameWithCopiesSetDTO> getAvailableGameWithCopiesSetDTOs(Timestamp startTime, Integer duration) {
        List<Game> allGames = gameRepository.findAll();

        List<GameCopy> availableGameCopies = getAvailableGameCopiesFor(startTime, duration);

        return allGames
                .stream()
                .map(game -> {
                    List<GameCopy> copies = new ArrayList<>();
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

    public List<GameCopyDTO> getAvailableGameCopyDTOsFor(Timestamp startTime, Integer duration) {
        return getAvailableGameCopiesFor(startTime, duration)
                .stream()
                .map(GameCopy::toDTO)
                .collect(Collectors.toList());
    }

    public List<GameCopyNameDTO> getAvailableGameCopyNameDTOsFor(Timestamp startTime, Integer duration) {
        return getAvailableGameCopiesFor(startTime, duration)
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
