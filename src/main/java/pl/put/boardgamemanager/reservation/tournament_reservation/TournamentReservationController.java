package pl.put.boardgamemanager.reservation.tournament_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TournamentReservationController {

    @Autowired
    private TournamentReservationRepository repository;

    @GetMapping("/tournament_reservations/{id}")
    public TournamentReservationDTO get(@PathVariable Long id) {
        TournamentReservation tournamentReservation = repository.findById(id).orElse(null);
        return TournamentReservation.toDTO(tournamentReservation);
    }

    @GetMapping("/tournament_reservations")
    public List<TournamentReservationDTO> all() {
        return repository.findAll().stream()
                .map(TournamentReservation::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/tournament_reservations")
    public void create(@RequestBody TournamentReservationDTO tournamentReservationDTO) {
        repository.save(TournamentReservation.fromDTO(tournamentReservationDTO));
    }

    @PutMapping("/tournament_reservations")
    public TournamentReservationDTO update(@RequestBody TournamentReservationDTO tournamentReservationDTO) {
        TournamentReservation tournamentReservation = TournamentReservation.fromDTO(tournamentReservationDTO);
        return repository.findById(tournamentReservation.getTableId())
                .map(currentTournamentReservation -> {
                    currentTournamentReservation.updateParams(tournamentReservation);
                    repository.save(currentTournamentReservation);
                    return TournamentReservation.toDTO(currentTournamentReservation);
                })
                .orElseGet(() -> {
                    repository.save(tournamentReservation);
                    return TournamentReservation.toDTO(tournamentReservation);
                });
    }

    @DeleteMapping("/tournament_reservations/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
