package pl.put.boardgamemanager.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.boardgamemanager.ListDTO;

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
    public ListDTO<TournamentDTO> all() {
        return service.all();
    }

    @PostMapping("/tournaments")
    public TournamentDTO create(@RequestBody TournamentDTO tournamentDTO) {
        if(!tournamentDTO.validate()) return tournamentDTO;
        else return service.create(tournamentDTO);
    }

    @PutMapping("/tournaments")
    public TournamentDTO update(@RequestBody TournamentDTO tournamentDTO) {
        if(tournamentDTO.getId() == null) {
            tournamentDTO.setErrorMessage("Id in updating cannot be null");
            return tournamentDTO;
        }
        if(!tournamentDTO.validate()) return tournamentDTO;
        else return service.update(tournamentDTO);
    }

    @DeleteMapping("/tournaments/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
