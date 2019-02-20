package pl.put.boardgamemanager.game_copy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.boardgamemanager.rental.private_rental.PrivateRentalDTO;

import java.sql.Timestamp;
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
    public GameCopyDTO create(@RequestBody GameCopyDTO gameCopyDTO) {
        return service.create(gameCopyDTO);
    }

    @PostMapping("/game_copies/available")
    public List<GameCopyNameDTO> getAvailableGameCopies(@RequestBody PrivateRentalDTO dto) {
        return service.getAvailableGameCopiesFor(Timestamp.valueOf(dto.getRentalTime()), dto.getDuration());
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
