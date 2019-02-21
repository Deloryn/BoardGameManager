package pl.put.boardgamemanager.rest.copy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.boardgamemanager.dto.CopyDTO;
import pl.put.boardgamemanager.dto.GameCopyNameDTO;
import pl.put.boardgamemanager.dto.GameWithCopiesSetDTO;
import pl.put.boardgamemanager.logic.copy.CopyService;
import pl.put.boardgamemanager.dto.PrivateRentalDTO;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin
public class CopyController {

    @Autowired
    private CopyService service;

    @GetMapping("/game_copies/{id}")
    public CopyDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/game_copies")
    public List<CopyDTO> all() {
        return service.all();
    }

    @GetMapping("/game_copies/count/{gameId}")
    public Integer countGames(@PathVariable Long gameId) {
        return service.countGames(gameId);
    }

    @PostMapping("/game_copies")
    public CopyDTO create(@RequestBody CopyDTO copyDTO) {
        return service.create(copyDTO);
    }

    @PostMapping("/game_copies/available-all")
    public List<GameWithCopiesSetDTO> getAvailableGameWithCopiesSetDTOs(@RequestBody PrivateRentalDTO dto) {
        return service.getAvailableGameWithCopiesSetDTOs(Timestamp.valueOf(dto.getRentalTime()), dto.getDuration());
    }

    @PostMapping("/game_copies/available-distinct")
    public List<GameCopyNameDTO> getAvailableGameCopies(@RequestBody PrivateRentalDTO dto) {
        return service.getAvailableGameCopyNameDTOsFor(Timestamp.valueOf(dto.getRentalTime()), dto.getDuration());
    }

    @PutMapping("/game_copies")
    public CopyDTO update(@RequestBody CopyDTO copyDTO) {
        return service.update(copyDTO);
    }

    @DeleteMapping("/game_copies/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
