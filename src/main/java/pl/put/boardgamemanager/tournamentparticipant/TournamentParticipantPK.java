package pl.put.boardgamemanager.tournamentparticipant;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TournamentParticipantPK implements Serializable {
    private int tournamentTournamentId;
    private int clientPersonId;

    @Column(name = "tournament_tournament_id")
    @Id
    public int getTournamentTournamentId() {
        return tournamentTournamentId;
    }

    public void setTournamentTournamentId(int tournamentTournamentId) {
        this.tournamentTournamentId = tournamentTournamentId;
    }

    @Column(name = "client_person_id")
    @Id
    public int getClientPersonId() {
        return clientPersonId;
    }

    public void setClientPersonId(int clientPersonId) {
        this.clientPersonId = clientPersonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TournamentParticipantPK that = (TournamentParticipantPK) o;
        return tournamentTournamentId == that.tournamentTournamentId &&
                clientPersonId == that.clientPersonId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tournamentTournamentId, clientPersonId);
    }
}
