package pl.put.boardgamemanager.tournament_participant;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TournamentParticipantPK implements Serializable {

    @Id
    @Column(name = "clientId", nullable = false)
    private Long clientId;

    @Id
    @Column(name = "tournamentid", nullable = false)
    private Long tournamentId;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TournamentParticipantPK that = (TournamentParticipantPK) o;
        return Objects.equals(clientId, that.clientId) &&
                Objects.equals(tournamentId, that.tournamentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, tournamentId);
    }
}
