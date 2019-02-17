package pl.put.boardgamemanager.tournament_participant;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tournamentparticipants", schema = "public", catalog = "postgres")
@IdClass(TournamentParticipantPK.class)
public class TournamentParticipant {

    @Id
    @Column(name = "clientid", nullable = false)
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
        if(!super.equals(o)) return false;
        else if (getClass() != o.getClass()) return false;
        else {
            TournamentParticipant that = (TournamentParticipant) o;
            return Objects.equals(clientId, that.clientId) &&
                    Objects.equals(tournamentId, that.tournamentId);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, tournamentId);
    }

}
