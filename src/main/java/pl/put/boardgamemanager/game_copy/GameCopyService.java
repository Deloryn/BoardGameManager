package pl.put.boardgamemanager.game_copy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.game.Game;
import pl.put.boardgamemanager.game.GameRepository;
import pl.put.boardgamemanager.game.GameWithCopiesSetDTO;
import pl.put.boardgamemanager.private_rental.PrivateRental;
import pl.put.boardgamemanager.private_rental.PrivateRentalRepository;
import pl.put.boardgamemanager.tournament_rental.TournamentRental;
import pl.put.boardgamemanager.tournament_rental.TournamentRentalRepository;
import pl.put.boardgamemanager.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    private List<GameCopy> getBusyRentalCopies(LocalDateTime rentalTime, Integer duration) {
        PrivateRental desiredRental = new PrivateRental();
        desiredRental.setStartTime(rentalTime);
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

    private List<GameCopy> getAvailableGameCopiesFor(LocalDateTime startTime, Integer duration) {
        List<GameCopy> allCopies = gameCopyRepository.findAll();
        allCopies.removeAll(getTournamentRentalGameCopies());
        allCopies.removeAll(getBusyRentalCopies(startTime, duration));

        return allCopies;
    }

    public List<GameWithCopiesSetDTO> getAvailableGameWithCopiesSetDTOs(LocalDateTime startTime, Integer duration) {
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

    public List<GameCopyNameDTO> getAvailableGameCopyNameDTOsFor(LocalDateTime startTime, Integer duration) {
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
                .filter(Utils.distinctByKey(GameCopyNameDTO::getName))
                .collect(Collectors.toList());

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
