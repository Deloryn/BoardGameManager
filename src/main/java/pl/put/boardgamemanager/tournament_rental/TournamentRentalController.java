package pl.put.boardgamemanager.tournament_rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.boardgamemanager.ListDTO;

import java.util.List;

@RestController
@CrossOrigin
public class TournamentRentalController {

    @Autowired
    private TournamentRentalService service;

    @GetMapping("/tournament_rentals/{id}")
    public TournamentRentalDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/tournament_rentals")
    public ListDTO<TournamentRentalDTO> all() {
        return service.all();
    }

    @GetMapping("/tournament_rentals/all-by-tournament-id/{tournamentId}")
    public ListDTO<TournamentRentalDTO> allByTournamentId(@PathVariable Long tournamentId) {
        return service.allByTournamentId(tournamentId);
    }

    @PostMapping("/tournament_rentals")
    public TournamentRentalDTO create(@RequestBody TournamentRentalDTO tournamentRentalDTO) {
        if(!tournamentRentalDTO.validate()) return tournamentRentalDTO;
        else return service.create(tournamentRentalDTO);
    }

    @PutMapping("/tournament_rentals")
    public TournamentRentalDTO update(@RequestBody TournamentRentalDTO tournamentRentalDTO) {
        if(tournamentRentalDTO.getId() == null) {
            tournamentRentalDTO.setErrorMessage("Id in updating cannot be null");
            return tournamentRentalDTO;
        }
        if(!tournamentRentalDTO.validate()) return tournamentRentalDTO;
        else return service.update(tournamentRentalDTO);
    }

    @DeleteMapping("/tournament_rentals/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
