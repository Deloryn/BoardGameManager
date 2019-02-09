package pl.put.boardgamemanager.tournamentparticipant;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "tournament_participants", schema = "public", catalog = "postgres")
@IdClass(TournamentParticipantPK.class)
public class TournamentParticipant {
    private int tournamentTournamentId;
    private int clientPersonId;

    @Id
    @Column(name = "tournament_tournament_id")
    public int getTournamentTournamentId() {
        return tournamentTournamentId;
    }

    public void setTournamentTournamentId(int tournamentTournamentId) {
        this.tournamentTournamentId = tournamentTournamentId;
    }

    @Id
    @Column(name = "client_person_id")
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
        TournamentParticipant that = (TournamentParticipant) o;
        return tournamentTournamentId == that.tournamentTournamentId &&
                clientPersonId == that.clientPersonId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tournamentTournamentId, clientPersonId);
    }
}
