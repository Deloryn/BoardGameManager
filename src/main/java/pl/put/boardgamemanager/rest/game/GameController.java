package pl.put.boardgamemanager.rest.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.boardgamemanager.dto.GameDTO;
import pl.put.boardgamemanager.logic.game.GameService;

import java.util.List;

@RestController
@CrossOrigin
public class GameController {

    @Autowired
    private GameService service;

    @GetMapping("/games/{id}")
    public GameDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/games")
    public List<GameDTO> all() {
        return service.all();
    }

    @PostMapping("/games")
    public GameDTO create(@RequestBody GameDTO gameDTO) {
        return service.create(gameDTO);
    }

    @PutMapping("/games")
    public GameDTO update(@RequestBody GameDTO gameDTO) {
        return service.update(gameDTO);
    }

    @DeleteMapping("/games/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
