package pl.put.boardgamemanager.rental;

import pl.put.boardgamemanager.game_copy.GameCopy;
import pl.put.boardgamemanager.rental.private_rental.PrivateRental;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rentals", schema = "public", catalog = "postgres")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Rental {

    @Id
    @Column(name = "copyid", nullable = false)
    protected Long copyId;

    @OneToOne
    @JoinColumn(name = "copyid", referencedColumnName = "id", nullable = false)
    protected GameCopy gameCopy;

    public Long getCopyId() {
        return copyId;
    }

    public void setCopyId(Long copyid) {
        this.copyId = copyid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental that = (Rental) o;
        return Objects.equals(copyId, that.copyId) &&
                Objects.equals(gameCopy, that.gameCopy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(copyId, gameCopy);
    }

    public GameCopy getGameCopy() {
        return gameCopy;
    }

    public void setGameCopy(GameCopy gameCopy) {
        this.gameCopy = gameCopy;
    }
}
