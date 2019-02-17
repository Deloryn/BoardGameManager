package pl.put.boardgamemanager.tournament_participant;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tournamentparticipants", schema = "public", catalog = "postgres")
public class TournamentParticipant {

    @EmbeddedId
    private TournamentParticipantPK primaryKey;

    public TournamentParticipantPK getPrimaryKey() { return this.primaryKey; }

    public void setPrimaryKey(TournamentParticipantPK primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o)) return false;
        else if (getClass() != o.getClass()) return false;
        else {
            TournamentParticipant that = (TournamentParticipant) o;
            return Objects.equals(primaryKey, that.primaryKey);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(primaryKey);
    }

}
