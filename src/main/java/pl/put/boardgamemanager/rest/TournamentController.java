package pl.put.boardgamemanager.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.boardgamemanager.dto.TournamentDTO;
import pl.put.boardgamemanager.logic.TournamentService;

import java.util.List;

@RestController
@CrossOrigin
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
    public TournamentDTO create(@RequestBody TournamentDTO tournamentDTO) {
        return service.create(tournamentDTO);
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
