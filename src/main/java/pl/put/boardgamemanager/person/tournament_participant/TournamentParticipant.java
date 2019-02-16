package pl.put.boardgamemanager.person.tournament_participant;

import pl.put.boardgamemanager.person.Person;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tournamentparticipants", schema = "public", catalog = "postgres")
@DiscriminatorValue("TournamentParticipant")
@IdClass(TournamentParticipantPK.class)
public class TournamentParticipant extends Person {
    @Id
    @Column(name = "tournamentid", nullable = false)
    private Integer tournamentId;

    public Integer getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Integer tournamentid) {
        this.tournamentId = tournamentid;
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o)) return false;
        else if (getClass() != o.getClass()) return false;
        else {
            TournamentParticipant that = (TournamentParticipant) o;
            return Objects.equals(tournamentId, that.tournamentId);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, phoneNumber, role, tournamentId);
    }

}
