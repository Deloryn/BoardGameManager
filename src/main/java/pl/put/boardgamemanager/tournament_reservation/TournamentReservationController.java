package pl.put.boardgamemanager.reservation.tournament_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TournamentReservationController {

    @Autowired
    private TournamentReservationService service;

    @GetMapping("/tournament_reservations/{id}")
    public TournamentReservationDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/tournament_reservations")
    public List<TournamentReservationDTO> all() {
        return service.all();
    }

    @PostMapping("/tournament_reservations")
    public TournamentReservationDTO create(@RequestBody TournamentReservationDTO tournamentReservationDTO) {
        return service.create(tournamentReservationDTO);
    }

    @PutMapping("/tournament_reservations")
    public TournamentReservationDTO update(@RequestBody TournamentReservationDTO tournamentReservationDTO) {
        return service.update(tournamentReservationDTO);
    }

    @DeleteMapping("/tournament_reservations/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
