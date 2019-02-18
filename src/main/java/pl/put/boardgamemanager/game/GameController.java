package pl.put.boardgamemanager.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GameController {

    @Autowired
    private GameRepository repository;

    @GetMapping("/games/{id}")
    public GameDTO get(@PathVariable Long id) {
        Game game = repository.findById(id).orElse(null);
        return Game.toDTO(game);
    }

    @GetMapping("/games")
    public List<GameDTO> all() {
        return repository.findAll().stream()
                .map(Game::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/games")
    public void create(@RequestBody GameDTO gameDTO) {
        repository.save(Game.fromDTO(gameDTO));
    }

    @PutMapping("/games")
    public GameDTO update(@RequestBody GameDTO gameDTO) {
        Game game = Game.fromDTO(gameDTO);
        return repository.findById(game.getId())
                .map(currentGame -> {
                    currentGame.updateParams(game);
                    repository.save(currentGame);
                    return Game.toDTO(currentGame);
                })
                .orElseGet(() -> {
                    repository.save(game);
                    return Game.toDTO(game);
                });
    }

    @DeleteMapping("/games/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
