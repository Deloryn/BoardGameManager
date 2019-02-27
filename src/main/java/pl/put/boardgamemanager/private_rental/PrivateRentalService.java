package pl.put.boardgamemanager.private_rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.ListDTO;
import pl.put.boardgamemanager.Utils;
import pl.put.boardgamemanager.game.GameRepository;
import pl.put.boardgamemanager.game_copy.GameCopy;
import pl.put.boardgamemanager.game_copy.GameCopyRepository;
import pl.put.boardgamemanager.tournament.Tournament;
import pl.put.boardgamemanager.tournament.TournamentRepository;
import pl.put.boardgamemanager.tournament_rental.TournamentRental;
import pl.put.boardgamemanager.tournament_rental.TournamentRentalRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivateRentalService {

    @Autowired
    private PrivateRentalRepository privateRentalRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameCopyRepository gameCopyRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private TournamentRentalRepository tournamentRentalRepository;

    public PrivateRentalDTO get(Long id) {
        PrivateRental rental = privateRentalRepository.findById(id).orElse(null);
        if(rental == null) {
            PrivateRentalDTO dto = new PrivateRentalDTO();
            dto.setErrorMessage("There is no private rental with the given id");
            return dto;
        }
        else return Utils.assignGameNameTo(rental.toDTO(), gameRepository, gameCopyRepository);
    }

    public ListDTO<PrivateRentalDTO> all() {
        ListDTO<PrivateRentalDTO> resultDTO = new ListDTO<>();
        resultDTO.setValues(privateRentalRepository.findAll().stream()
                .map(PrivateRental::toDTO)
                .map(dto -> Utils.assignGameNameTo(dto, gameRepository, gameCopyRepository))
                .collect(Collectors.toList()));
        return resultDTO;
    }

    public PrivateRentalDTO create(PrivateRentalDTO dto) {
        PrivateRental rental = new PrivateRental();
        if(dto.getGameId() != null){
            Long copyId = getAnyCopyId(dto.getStartTime(), dto.getDuration(), dto.getGameId());
            if(copyId == null){
                dto.setErrorMessage("Brak dostępnych egzemplarzy gry");
                return dto;
            }
            dto.setCopyId(copyId);
        }
        rental.updateParamsFrom(dto);

        try {
            privateRentalRepository.save(rental);
            return Utils.assignGameNameTo(rental.toDTO(), gameRepository, gameCopyRepository);
        }
        catch(DataIntegrityViolationException ex) {
            dto.setErrorMessage("Given data violates data constraints");
            return dto;
        }
    }

    public PrivateRentalDTO update(PrivateRentalDTO dto) {
        if(dto.getGameId() != null){
            Long copyId = getAnyCopyId(dto.getStartTime(), dto.getDuration(), dto.getGameId());
            if(copyId == null){
                dto.setErrorMessage("Brak dostępnych egzemplarzy gry");
                return dto;
            }
            dto.setCopyId(copyId);
        }
        return privateRentalRepository.findById(dto.getId())
                .map(existingRental -> {
                    existingRental.updateParamsFrom(dto);

                    try {
                        privateRentalRepository.save(existingRental);
                        return Utils.assignGameNameTo(existingRental.toDTO(), gameRepository, gameCopyRepository);
                    }
                    catch(DataIntegrityViolationException ex) {
                        dto.setErrorMessage("Given data violates data constraints");
                        return dto;
                    }

                })
                .orElseGet(() -> create(dto));
    }

    public void delete(Long id) {
        privateRentalRepository.deleteById(id);
    }

    private Long getAnyCopyId(LocalDateTime startTime, Integer duration, Long gameId) {
        List<GameCopy> availableGameCopies = getAvailableGameCopies(startTime, duration, gameId);
        if (availableGameCopies.isEmpty()){
            return null;
        }
        return availableGameCopies.get(0).getId();
    }

    private List<GameCopy> getAvailableGameCopies(LocalDateTime startTime, Integer duration, Long gameId) {
        List<GameCopy> allCopies = gameCopyRepository.findAllByGameId(gameId);
        allCopies.removeAll(getBusyRentalCopies(startTime, duration));
        allCopies.removeAll(getTournamentRentalGameCopies(startTime, duration));

        return allCopies;
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

    private List<GameCopy> getTournamentRentalGameCopies(LocalDateTime rentalTime, Integer duration) {
        PrivateRental desiredRental = new PrivateRental();
        desiredRental.setStartTime(rentalTime);
        desiredRental.setDuration(duration);

        return tournamentRepository
                .findAll()
                .stream()
                .filter(tournament -> Utils.isEventDuringAnother(tournament, desiredRental))
                .map(Tournament::getId)
                .map(tournamentId -> tournamentRentalRepository.findAllByTournamentId(tournamentId))
                .flatMap(List::stream)
                .map(TournamentRental::getCopyId)
                .map(copyId -> gameCopyRepository.findById(copyId).orElse(null))
                .collect(Collectors.toList());
    }
}
