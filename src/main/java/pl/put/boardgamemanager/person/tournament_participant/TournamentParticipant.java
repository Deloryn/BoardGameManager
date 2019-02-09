package pl.put.boardgamemanager.person.tournament_participant;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "tournament_participants", schema = "public", catalog = "postgres")
@IdClass(TournamentParticipantPK.class)
public class TournamentParticipant {
    private int tournamentId;
    private int clientId;

    @Id
    @Column(name = "tournament_tournament_id")
    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentTournamentId) {
        this.tournamentId = tournamentTournamentId;
    }

    @Id
    @Column(name = "client_person_id")
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
        TournamentParticipant that = (TournamentParticipant) o;
        return tournamentId == that.tournamentId &&
                clientId == that.clientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tournamentId, clientId);
    }
}
