package pl.put.boardgamemanager.tournament_participant;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TournamentParticipantPK implements Serializable {

    @Column(name = "clientId", nullable = false)
    private Long clientId;

    @Column(name = "tournamentid", nullable = false)
    private Long tournamentId;

    TournamentParticipantPK() {
        
    }

    TournamentParticipantPK(Long clientId, Long tournamentId) {
        setClientId(clientId);
        setTournamentId(tournamentId);
    }

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
