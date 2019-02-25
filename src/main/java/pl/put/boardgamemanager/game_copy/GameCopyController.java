package pl.put.boardgamemanager.game_copy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.boardgamemanager.ListDTO;
import pl.put.boardgamemanager.TimeAndTargetDTO;
import pl.put.boardgamemanager.ValueDTO;
import pl.put.boardgamemanager.game.GameWithCopiesSetDTO;

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
    public ListDTO<GameCopyDTO> all() {
        return service.all();
    }

    @GetMapping("/game_copies/count/{gameId}")
    public ValueDTO<Integer> countGames(@PathVariable Long gameId) {
        return service.countGames(gameId);
    }

    @PostMapping("/game_copies")
    public GameCopyDTO create(@RequestBody GameCopyDTO gameCopyDTO) {
        if(!gameCopyDTO.validate()) return gameCopyDTO;
        else return service.create(gameCopyDTO);
    }

    @PostMapping("/game_copies/available-at-private")
    public ListDTO<GameWithCopiesSetDTO> getAvailableGameWithCopiesSetDTOsForPrivate(@RequestBody TimeAndTargetDTO dto) {
        if(!dto.validate()) {
            ListDTO<GameWithCopiesSetDTO> resultDTO = new ListDTO<>();
            resultDTO.setErrorMessage(dto.getErrorMessage());
            return resultDTO;
        }
        return service.getAvailableGameWithCopiesSetDTOsForPrivate(dto.getStartTime(), dto.getDuration(), dto.getTargetId());
    }

    @PostMapping("/game_copies/available-at-tournament")
    public ListDTO<GameWithCopiesSetDTO> getAvailableGameWithCopiesSetDTOsForTournament(@RequestBody TimeAndTargetDTO dto) {
        if(!dto.validate()) {
            ListDTO<GameWithCopiesSetDTO> resultDTO = new ListDTO<>();
            resultDTO.setErrorMessage(dto.getErrorMessage());
            return resultDTO;
        }
        return service.getAvailableGameWithCopiesSetDTOsForTournament(dto.getStartTime(), dto.getDuration(), dto.getTargetId());
    }

    @PutMapping("/game_copies")
    public GameCopyDTO update(@RequestBody GameCopyDTO gameCopyDTO) {
        if(gameCopyDTO.getId() == null) {
            gameCopyDTO.setErrorMessage("Id in updating cannot be null");
            return gameCopyDTO;
        }
        if(!gameCopyDTO.validate()) return gameCopyDTO;
        else return service.update(gameCopyDTO);
    }

    @DeleteMapping("/game_copies/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
