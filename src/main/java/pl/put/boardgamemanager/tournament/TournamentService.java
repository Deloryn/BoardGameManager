package pl.put.boardgamemanager.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.game.Game;
import pl.put.boardgamemanager.game.GameRepository;
import pl.put.boardgamemanager.tournament_rental.TournamentRental;
import pl.put.boardgamemanager.tournament_rental.TournamentRentalRepository;
import pl.put.boardgamemanager.reservation.tournament_reservation.TournamentReservation;
import pl.put.boardgamemanager.reservation.tournament_reservation.TournamentReservationRepository;
import pl.put.boardgamemanager.table.Table;
import pl.put.boardgamemanager.table.TableRepository;

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

    public TournamentDTO get(Long id) {
        Tournament tournament = tournamentRepository.findById(id).orElse(null);
        if(tournament == null) return null;
        else return tournament.toDTO();
    }

    private boolean validateTournamentDTO(TournamentDTO dto) {

        // TODO: again check if tables and copies are available

        Integer numberOfCopies = dto.getCopyIds().size();
        if(numberOfCopies.equals(0) || dto.getTableIds().size() == 0) return false;
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
        if(game == null) return false;
        else {
            Short gameMaxPlayers = game.getMaxPlayers();
            Integer tournamentMaxPlayers = min(numberOfCopies*gameMaxPlayers, totalOfSits);
            return dto.getMaxPlayers() > tournamentMaxPlayers;
        }
    }

    public List<TournamentDTO> all() {
        return tournamentRepository.findAll().stream()
                .map(Tournament::toDTO)
                .collect(Collectors.toList());
    }

    public TournamentDTO create(TournamentDTO dto) {
        if(!validateTournamentDTO(dto)) return null;
        else {
            Tournament tournament = new Tournament();
            tournament.updateParamsFrom(dto);
            tournamentRepository.save(tournament);

            dto.getTableIds().forEach(tableId -> {
                TournamentReservation reservation = new TournamentReservation();
                reservation.setTableId(tableId);
                reservation.setTournamentId(tournament.getId());
                tournamentReservationRepository.save(reservation);
            });

            dto.getCopyIds().forEach(copyId -> {
                TournamentRental rental = new TournamentRental();
                rental.setCopyId(copyId);
                rental.setTournamentId(tournament.getId());
                tournamentRentalRepository.save(rental);
            });

            return tournament.toDTO();
        }
    }

    public TournamentDTO update(TournamentDTO dto) {
        return tournamentRepository.findById(dto.getId())
                .map(existingTournament -> {
                    existingTournament.updateParamsFrom(dto);
                    tournamentRepository.save(existingTournament);
                    return existingTournament.toDTO();
                })
                .orElseGet(() -> create(dto));
    }

    public void delete(Long id) {
        tournamentRepository.deleteById(id);
    }

}
