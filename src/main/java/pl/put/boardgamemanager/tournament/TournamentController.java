package pl.put.boardgamemanager.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TournamentController {

    @Autowired
    private TournamentRepository repository;

    @GetMapping("/tournaments/{id}")
    public TournamentDTO get(@PathVariable Long id) {
        Tournament tournament = repository.findById(id).orElse(null);
        return Tournament.toDTO(tournament);
    }

    @GetMapping("/tournaments")
    public List<TournamentDTO> all() {
        return repository.findAll().stream()
                .map(Tournament::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/tournaments")
    public void create(@RequestBody TournamentDTO tournamentDTO) {
        repository.save(Tournament.fromDTO(tournamentDTO));
    }

    @PutMapping("/tournaments")
    public TournamentDTO update(@RequestBody TournamentDTO tournamentDTO) {
        Tournament tournament = Tournament.fromDTO(tournamentDTO);
        return repository.findById(tournament.getId())
                .map(currentTournament -> {
                    currentTournament.updateParams(tournament);
                    repository.save(currentTournament);
                    return Tournament.toDTO(currentTournament);
                })
                .orElseGet(() -> {
                    repository.save(tournament);
                    return Tournament.toDTO(tournament);
                });
    }

    @DeleteMapping("/tournaments/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
