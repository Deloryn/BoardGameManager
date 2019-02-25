package pl.put.boardgamemanager.tournament_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.boardgamemanager.ListDTO;

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
    public ListDTO<TournamentReservationDTO> all() {
        return service.all();
    }

    @GetMapping("/tournament_reservations/all-by-tournament-id/{tournamentId}")
    public ListDTO<TournamentReservationDTO> allByTournamentId(@PathVariable Long tournamentId) {
        return service.allByTournamentId(tournamentId);
    }

    @PostMapping("/tournament_reservations")
    public TournamentReservationDTO create(@RequestBody TournamentReservationDTO tournamentReservationDTO) {
        if(!tournamentReservationDTO.validate()) return tournamentReservationDTO;
        else return service.create(tournamentReservationDTO);
    }

    @PutMapping("/tournament_reservations")
    public TournamentReservationDTO update(@RequestBody TournamentReservationDTO tournamentReservationDTO) {
        if(tournamentReservationDTO.getId() == null) {
            tournamentReservationDTO.setErrorMessage("Id in updating cannot be null");
            return tournamentReservationDTO;
        }
        if(!tournamentReservationDTO.validate()) return tournamentReservationDTO;
        else return service.update(tournamentReservationDTO);
    }

    @DeleteMapping("/tournament_reservations/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
