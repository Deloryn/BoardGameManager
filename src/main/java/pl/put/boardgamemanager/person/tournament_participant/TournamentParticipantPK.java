package pl.put.boardgamemanager.person.tournament_participant;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TournamentParticipantPK implements Serializable {
    private int tournamentId;
    private int clientId;

    @Column(name = "tournament_tournament_id")
    @Id
    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentTournamentId) {
        this.tournamentId = tournamentTournamentId;
    }

    @Column(name = "client_person_id")
    @Id
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientPersonId) {
        this.clientId = clientPersonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TournamentParticipantPK that = (TournamentParticipantPK) o;
        return tournamentId == that.tournamentId &&
                clientId == that.clientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tournamentId, clientId);
    }
}
