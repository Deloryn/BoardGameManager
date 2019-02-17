package pl.put.boardgamemanager.rental.tournament_rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TournamentRentalController {

    @Autowired
    private TournamentRentalRepository repository;

    @GetMapping("/tournament_rentals/{id}")
    public TournamentRentalDTO get(@PathVariable Long id) {
        TournamentRental tournamentRental = repository.findById(id).orElse(null);
        return TournamentRental.toDTO(tournamentRental);
    }

    @GetMapping("/tournament_rentals")
    public List<TournamentRentalDTO> all() {
        List<TournamentRentalDTO> dtos = new ArrayList<>();
        repository.findAll().forEach(tournamentRental -> {
            dtos.add(TournamentRental.toDTO(tournamentRental));
        });
        return dtos;
    }

    @PostMapping("/tournament_rentals")
    public void create(@RequestBody TournamentRentalDTO tournamentRentalDTO) {
        repository.save(TournamentRental.fromDTO(tournamentRentalDTO));
    }

    @PutMapping("/tournament_rentals")
    public TournamentRentalDTO update(@RequestBody TournamentRentalDTO tournamentRentalDTO) {
        TournamentRental tournamentRental = TournamentRental.fromDTO(tournamentRentalDTO);
        return repository.findById(tournamentRental.getCopyId())
                .map(currentTournamentRental -> {
                    currentTournamentRental.updateParams(currentTournamentRental);
                    repository.save(currentTournamentRental);
                    return TournamentRental.toDTO(currentTournamentRental);
                })
                .orElseGet(() -> {
                    repository.save(tournamentRental);
                    return TournamentRental.toDTO(tournamentRental);
                });
    }

    @DeleteMapping("/tournament_rentals/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
