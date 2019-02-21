package pl.put.boardgamemanager.dto;

import pl.put.boardgamemanager.model.copy.Copy;
import pl.put.boardgamemanager.model.game.Game;

import java.util.List;

public class GameWithCopiesSetDTO extends DTO {

    private Game game;

    private List<Copy> gameCopies;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Copy> getGameCopies() {
        return gameCopies;
    }

    public void setGameCopies(List<Copy> gameCopies) {
        this.gameCopies = gameCopies;
    }

}
