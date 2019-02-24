package pl.put.boardgamemanager.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.ListDTO;
import pl.put.boardgamemanager.game.Game;
import pl.put.boardgamemanager.game.GameRepository;
import pl.put.boardgamemanager.tournament_participant.TournamentParticipant;
import pl.put.boardgamemanager.tournament_participant.TournamentParticipantRepository;
import pl.put.boardgamemanager.tournament_rental.TournamentRental;
import pl.put.boardgamemanager.tournament_rental.TournamentRentalRepository;
import pl.put.boardgamemanager.tournament_reservation.TournamentReservation;
import pl.put.boardgamemanager.tournament_reservation.TournamentReservationRepository;
import pl.put.boardgamemanager.table.Table;
import pl.put.boardgamemanager.table.TableRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.primitives.Ints.min;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private TournamentRentalRepository tournamentRentalRepository;

    @Autowired
    private TournamentReservationRepository tournamentReservationRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private TournamentParticipantRepository tournamentParticipantRepository;

    public TournamentDTO get(Long id) {
        Tournament tournament = tournamentRepository.findById(id).orElse(null);
        if (tournament == null) {
            TournamentDTO dto = new TournamentDTO();
            dto.setErrorMessage("There is no tournament with the given id");
            return dto;
        }
        else return tournament.toDTO();
    }

    private boolean validateTournamentDTO(TournamentDTO dto) {

        // TODO: again check if tables and copies are available
        if (dto.getCopyIds() == null || dto.getTableIds() == null) {
            dto.setErrorMessage("Neither CopyIds nor TableIds cannot be null in creating a new tournament");
            return false;
        }

        Integer numberOfCopies = dto.getCopyIds().size();
        if (numberOfCopies.equals(0) || dto.getTableIds().size() == 0) {
            dto.setErrorMessage("Neither CopyIds nor TableIds cannot be empty in creating a new tournament");
            return false;
        }
        Integer totalOfSits = dto
                .getTableIds()
                .stream()
                .map(tableId -> tableRepository.findById(tableId).orElse(null))
                .collect(Collectors.toList())
                .stream()
                .map(Table::getNumberOfSits)
                .map(Integer::new)
                .reduce(0, Integer::sum);

        Game game = gameRepository.findById(dto.getGameId()).orElse(null);
        if (game == null) {
            dto.setErrorMessage("There is no game with given gameId");
            return false;
        }
        else {
            Short gameMaxPlayers = game.getMaxPlayers();
            Integer tournamentMaxPlayers = min(numberOfCopies * gameMaxPlayers, totalOfSits);
            return dto.getMaxPlayers() > tournamentMaxPlayers;
        }
    }

    public ListDTO<TournamentDTO> all() {
        ListDTO<TournamentDTO> resultDTO = new ListDTO<>();

        resultDTO.setValues(tournamentRepository.findAll().stream()
                .map(Tournament::toDTO)
                .map(dto -> {
                    Game game = gameRepository.findById(dto.getGameId()).orElse(null);
                    if (game != null) {
                        dto.setReadOnlyGameName(game.getName());
                    }
                    else {
                        dto.setErrorMessage("There is no game with the given id");
                    }
                    List<TournamentParticipant> allParticipants = tournamentParticipantRepository.findAll();
                    Integer numberOfTournamentPlayers =
                            allParticipants
                                    .stream()
                                    .filter(participant -> participant.getPrimaryKey().getTournamentId().equals(dto.getId()))
                                    .collect(Collectors.toList())
                                    .size();
                    dto.setReadOnlyPlayersNumber(numberOfTournamentPlayers);
                    return dto;
                })
                .collect(Collectors.toList()));

        return resultDTO;
    }

    @Transactional
    public TournamentDTO create(TournamentDTO dto) {
        if (!validateTournamentDTO(dto)) return dto;
        else {
            Tournament tournament = new Tournament();
            tournament.updateParamsFrom(dto);
            tournamentRepository.save(tournament);

            dto.getTableIds().forEach(tableId -> {
                TournamentReservation reservation = new TournamentReservation();
                reservation.setTableId(tableId);
                reservation.setTournamentId(tournament.getId());
                tournamentReservationRepository.save(reservation);

                try {
                    tournamentReservationRepository.save(reservation);
                }
                catch(DataIntegrityViolationException ex) {
                    dto.setErrorMessage("Given data violates data constraints");
                }
            });

            dto.getCopyIds().forEach(copyId -> {
                TournamentRental rental = new TournamentRental();
                rental.setCopyId(copyId);
                rental.setTournamentId(tournament.getId());
                tournamentRentalRepository.save(rental);

                try {
                    tournamentRentalRepository.save(rental);
                }
                catch(DataIntegrityViolationException ex) {
                    dto.setErrorMessage("Given data violates data constraints");
                }
            });

            if(dto.getErrorMessage() == null) return tournament.toDTO();
            else return dto;
        }
    }

    public TournamentDTO update(TournamentDTO dto) {
        return tournamentRepository.findById(dto.getId())
                .map(existingTournament -> {
                    existingTournament.updateParamsFrom(dto);
                    try {
                        tournamentRepository.save(existingTournament);
                        return existingTournament.toDTO();
                    }
                    catch(DataIntegrityViolationException ex) {
                        dto.setErrorMessage("Given data violates data constraints");
                        return dto;
                    }
                })
                .orElseGet(() -> create(dto));
    }

    public void delete(Long id) {
        tournamentRepository.deleteById(id);
    }

}
