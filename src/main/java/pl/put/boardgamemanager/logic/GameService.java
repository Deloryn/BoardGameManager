package pl.put.boardgamemanager.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.model.Game;
import pl.put.boardgamemanager.dto.GameDTO;
import pl.put.boardgamemanager.logic.repository.GameRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    private GameRepository repository;

    public GameDTO get(Long id) {
        Game game = repository.findById(id).orElse(null);
        if(game == null) return null;
        else return game.toDTO();
    }

    public List<GameDTO> all() {
        return repository.findAll().stream()
                .map(Game::toDTO)
                .collect(Collectors.toList());
    }

    public GameDTO create(GameDTO dto) {
        Game game = new Game();
        game.updateParamsFrom(dto);
        repository.save(game);
        return game.toDTO();
    }

    public GameDTO update(GameDTO dto) {
        return repository.findById(dto.getId())
                .map(existingGame -> {
                    existingGame.updateParamsFrom(dto);
                    repository.save(existingGame);
                    return existingGame.toDTO();
                })
                .orElseGet(() -> create(dto));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
