package pl.put.boardgamemanager.rental;

import pl.put.boardgamemanager.game_copy.GameCopy;
import pl.put.boardgamemanager.rental.private_rental.PrivateRental;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rentals", schema = "public", catalog = "postgres")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "copyid")
public abstract class Rental {

    @Id
    @Column(name = "copyid", nullable = false)
    protected Long copyId;

    @Column(name = "type", length = 1)
    protected Character type;

    @OneToOne
    @JoinColumn(name = "copyid", referencedColumnName = "id", nullable = false)
    protected GameCopy gameCopy;

    public Long getCopyId() {
        return copyId;
    }

    public void setCopyId(Long copyid) {
        this.copyId = copyid;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental that = (Rental) o;
        return Objects.equals(copyId, that.copyId) &&
                Objects.equals(type, that.type) &&
                Objects.equals(gameCopy, that.gameCopy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(copyId, type, gameCopy);
    }

    public GameCopy getGameCopy() {
        return gameCopy;
    }

    public void setGameCopy(GameCopy gameCopy) {
        this.gameCopy = gameCopy;
    }
}
