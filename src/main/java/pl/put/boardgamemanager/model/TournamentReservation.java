package pl.put.boardgamemanager.model;

import pl.put.boardgamemanager.dto.TournamentReservationDTO;

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
        return Objects.hash(id, tableId, tutorId, tournamentId);
    }

    public void updateParamsFrom(TournamentReservationDTO dto) {
        this.setTableId(dto.getTableId());
        this.setTutorId(dto.getTutorId());
        this.setTournamentId(dto.getTournamentId());
    }

    public TournamentReservationDTO toDTO() {
        TournamentReservationDTO dto = new TournamentReservationDTO();

        dto.setId(id);
        dto.setTableId(tableId);
        dto.setTutorId(tutorId);
        dto.setTournamentId(tournamentId);

        return dto;
    }

}
