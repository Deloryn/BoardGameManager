package pl.put.boardgamemanager.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TournamentController {

    @Autowired
    private TournamentService service;

    @GetMapping("/tournaments/{id}")
    public TournamentDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/tournaments")
    public List<TournamentDTO> all() {
        return service.all();
    }

    @PostMapping("/tournaments")
    public void create(@RequestBody TournamentDTO tournamentDTO) {
        service.create(tournamentDTO);
    }

    @PutMapping("/tournaments")
    public TournamentDTO update(@RequestBody TournamentDTO tournamentDTO) {
        return service.update(tournamentDTO);
    }

    @DeleteMapping("/tournaments/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
