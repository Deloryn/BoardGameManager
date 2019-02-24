package pl.put.boardgamemanager.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.boardgamemanager.ListDTO;
import pl.put.boardgamemanager.TimeDTO;
import pl.put.boardgamemanager.ValueDTO;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
public class TournamentController {

    @Autowired
    private TournamentService service;

    @PostMapping("/tournaments/calculate-finish-time")
    public ValueDTO<LocalDateTime> calculateFinishTime(@RequestBody TimeDTO timeDTO) {
        if(!timeDTO.validate()) {
            ValueDTO<LocalDateTime> resultDTO = new ValueDTO<>();
            resultDTO.setErrorMessage(timeDTO.getErrorMessage());
            return resultDTO;
        }
        else return service.calculateFinishTime(timeDTO.getStartTime(), timeDTO.getDuration());
    }

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
