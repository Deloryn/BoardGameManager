package pl.put.boardgamemanager.dto;

import pl.put.boardgamemanager.model.GameCopy;
import pl.put.boardgamemanager.model.Game;

import java.util.List;

public class GameWithCopiesSetDTO {

    private Game game;

    private List<GameCopy> gameCopies;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<GameCopy> getGameCopies() {
        return gameCopies;
    }

    public void setGameCopies(List<GameCopy> gameCopies) {
        this.gameCopies = gameCopies;
    }

}
