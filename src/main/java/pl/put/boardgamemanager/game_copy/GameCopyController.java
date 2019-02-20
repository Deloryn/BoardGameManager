package pl.put.boardgamemanager.game_copy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GameCopyController {

    @Autowired
    private GameCopyService service;

    @GetMapping("/game_copies/{id}")
    public GameCopyDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/game_copies")
    public List<GameCopyDTO> all() {
        return service.all();
    }

    @GetMapping("/game_copies/count/{gameId}")
    public Integer countGames(@PathVariable Long gameId) {
        return service.countGames(gameId);
    }

    @PostMapping("/game_copies")
    public void create(@RequestBody GameCopyDTO gameCopyDTO) {
        service.create(gameCopyDTO);
    }

    @PutMapping("/game_copies")
    public GameCopyDTO update(@RequestBody GameCopyDTO gameCopyDTO) {
        return service.update(gameCopyDTO);
    }

    @DeleteMapping("/game_copies/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
