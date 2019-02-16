package pl.put.boardgamemanager.person.tournament_participant;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TournamentParticipantPK implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Id
    @Column(name = "tournamentid", nullable = false)
    private Integer tournamentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Integer tournamentId) {
        this.tournamentId = tournamentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TournamentParticipantPK that = (TournamentParticipantPK) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(tournamentId, that.tournamentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tournamentId);
    }
}
