package pl.put.boardgamemanager.person.tournament_participant;

import pl.put.boardgamemanager.person.Person;
import pl.put.boardgamemanager.tournament.Tournament;

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

    @OneToOne
    @JoinColumn(name = "tournamentid", referencedColumnName = "id", nullable = false)
    private Tournament tournament;

    public Integer getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Integer tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o)) return false;
        else if (getClass() != o.getClass()) return false;
        else {
            TournamentParticipant that = (TournamentParticipant) o;
            return Objects.equals(tournamentId, that.tournamentId) &&
                    Objects.equals(tournament, that.tournament);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, phoneNumber, role, tournamentId, tournament);
    }

}
