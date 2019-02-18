package pl.put.boardgamemanager.rental.tournament_rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TournamentRentalController {

    @Autowired
    private TournamentRentalService service;

    @GetMapping("/tournament_rentals/{id}")
    public TournamentRentalDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/tournament_rentals")
    public List<TournamentRentalDTO> all() {
        return service.all();
    }

    @PostMapping("/tournament_rentals")
    public void create(@RequestBody TournamentRentalDTO tournamentRentalDTO) {
        service.create(tournamentRentalDTO);
    }

    @PutMapping("/tournament_rentals")
    public TournamentRentalDTO update(@RequestBody TournamentRentalDTO tournamentRentalDTO) {
        return service.update(tournamentRentalDTO);
    }

    @DeleteMapping("/tournament_rentals/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
