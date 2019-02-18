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
        if(gameCopy == null) return null;
        else return gameCopy.toDTO();
    }

    public List<GameCopyDTO> all() {
        return repository.findAll().stream()
                .map(GameCopy::toDTO)
                .collect(Collectors.toList());
    }

    public GameCopyDTO create(GameCopyDTO dto) {
        GameCopy gameCopy = new GameCopy();
        gameCopy.updateParamsFrom(dto);
        repository.save(gameCopy);
        return gameCopy.toDTO();
    }

    public GameCopyDTO update(GameCopyDTO dto) {
        return repository.findById(dto.getId())
                .map(existingCopy -> {
                    existingCopy.updateParamsFrom(dto);
                    repository.save(existingCopy);
                    return existingCopy.toDTO();
                })
                .orElseGet(() -> create(dto));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
