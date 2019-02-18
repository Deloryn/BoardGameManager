package pl.put.boardgamemanager.game_copy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameCopyService {

    @Autowired
    private GameCopyRepository repository;

    public GameCopyDTO get(Long id) {
        GameCopy gameCopy = repository.findById(id).orElse(null);
        return GameCopy.toDTO(gameCopy);
    }

    public List<GameCopyDTO> all() {
        return repository.findAll().stream()
                .map(GameCopy::toDTO)
                .collect(Collectors.toList());
    }

    public void create(GameCopyDTO gameCopyDTO) {
        repository.save(GameCopy.fromDTO(gameCopyDTO));
    }

    public GameCopyDTO update(GameCopyDTO gameCopyDTO) {
        GameCopy gameCopy = GameCopy.fromDTO(gameCopyDTO);
        return repository.findById(gameCopy.getId())
                .map(currentGameCopy -> {
                    currentGameCopy.updateParams(gameCopy);
                    repository.save(currentGameCopy);
                    return GameCopy.toDTO(currentGameCopy);
                })
                .orElseGet(() -> {
                    create(gameCopyDTO);
                    return gameCopyDTO;
                });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
