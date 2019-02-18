package pl.put.boardgamemanager.game_copy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GameCopyController {

    @Autowired
    private GameCopyRepository repository;

    @GetMapping("/game_copies/{id}")
    public GameCopyDTO get(@PathVariable Long id) {
        GameCopy gameCopy = repository.findById(id).orElse(null);
        return GameCopy.toDTO(gameCopy);
    }

    @GetMapping("/game_copies")
    public List<GameCopyDTO> all() {
        List<GameCopyDTO> dtos = new ArrayList<>();
        repository.findAll().forEach(gameCopy -> {
            dtos.add(GameCopy.toDTO(gameCopy));
        });
        return dtos;
    }

    @PostMapping("/game_copies")
    public void create(@RequestBody GameCopyDTO gameCopyDTO) {
        repository.save(GameCopy.fromDTO(gameCopyDTO));
    }

    @PutMapping("/game_copies")
    public GameCopyDTO update(@RequestBody GameCopyDTO gameCopyDTO) {
        GameCopy gameCopy = GameCopy.fromDTO(gameCopyDTO);
        return repository.findById(gameCopy.getId())
                .map(currentGameCopy -> {
                    currentGameCopy.updateParams(gameCopy);
                    repository.save(currentGameCopy);
                    return GameCopy.toDTO(currentGameCopy);
                })
                .orElseGet(() -> {
                    repository.save(gameCopy);
                    return GameCopy.toDTO(gameCopy);
                });
    }

    @DeleteMapping("/game_copies/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
