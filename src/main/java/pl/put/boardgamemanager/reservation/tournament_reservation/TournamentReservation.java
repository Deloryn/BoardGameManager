package pl.put.boardgamemanager.reservation.tournament_reservation;

import pl.put.boardgamemanager.reservation.Reservation;
import pl.put.boardgamemanager.tournament.Tournament;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tournamentreservations", schema = "public", catalog = "postgres")
@DiscriminatorValue("t")
public class TournamentReservation extends Reservation {

    @Column(name = "tournamentid", nullable = false, unique = true)
    private Long tournamentId;

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o)) return false;
        else if(getClass() != o.getClass()) return false;
        else {
            TournamentReservation that = (TournamentReservation) o;
            return Objects.equals(tournamentId, that.tournamentId);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId, tutorId, tournamentId);
    }

    public void updateParams(TournamentReservation tournamentReservation) {
        this.setTutorId(tournamentReservation.getTutorId());
        this.setTournamentId(tournamentReservation.getTournamentId());
    }

    public static TournamentReservation fromDTO(TournamentReservationDTO dto) {

        if(dto == null) return null;

        TournamentReservation tournamentReservation = new TournamentReservation();

        tournamentReservation.setTableId(dto.getTableId());
        tournamentReservation.setTutorId(dto.getTutorId());
        tournamentReservation.setTournamentId(dto.getTournamentId());

        return tournamentReservation;
    }

    public static TournamentReservationDTO toDTO(TournamentReservation tournamentReservation) {

        if(tournamentReservation == null) return null;

        TournamentReservationDTO dto = new TournamentReservationDTO();

        dto.setTutorId(tournamentReservation.getTutorId());
        dto.setTournamentId(tournamentReservation.getTournamentId());

        return dto;
    }

}
