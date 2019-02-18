package pl.put.boardgamemanager.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    private GameRepository repository;

    public GameDTO get(Long id) {
        Game game = repository.findById(id).orElse(null);
        return Game.toDTO(game);
    }

    public List<GameDTO> all() {
        return repository.findAll().stream()
                .map(Game::toDTO)
                .collect(Collectors.toList());
    }

    public void create(GameDTO gameDTO) {
        repository.save(Game.fromDTO(gameDTO));
    }

    public GameDTO update(GameDTO gameDTO) {
        Game game = Game.fromDTO(gameDTO);
        return repository.findById(game.getId())
                .map(currentGame -> {
                    currentGame.updateParams(game);
                    repository.save(currentGame);
                    return Game.toDTO(currentGame);
                })
                .orElseGet(() -> {
                    create(gameDTO);
                    return gameDTO;
                });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
