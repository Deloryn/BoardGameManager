package pl.put.boardgamemanager.game_copy;

import pl.put.boardgamemanager.game.Game;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "gamecopies", schema = "public", catalog = "postgres")
public class GameCopy {

    @SequenceGenerator(name = "gamecopies_seq", sequenceName = "gamecopies_seq")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gamecopies_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gameid", referencedColumnName = "id", nullable = false)
    private Game game;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCopy that = (GameCopy) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(game, that.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, game);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
