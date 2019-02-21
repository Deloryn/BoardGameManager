package pl.put.boardgamemanager.model.reservation.tournament_reservation;

import pl.put.boardgamemanager.dto.DTO;
import pl.put.boardgamemanager.dto.TournamentReservationDTO;
import pl.put.boardgamemanager.model.Model;
import pl.put.boardgamemanager.model.reservation.Reservation;

import javax.persistence.*;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "tournamentreservations", schema = "public", catalog = "postgres")
@DiscriminatorValue("t")
public class TournamentReservation extends Reservation implements Model {

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

    public void updateParamsFrom(DTO dto) {
        if(dto instanceof TournamentReservationDTO) {
            TournamentReservationDTO tournamentReservationDTO = (TournamentReservationDTO) dto;
            this.setTableId(tournamentReservationDTO.getTableId());
            this.setTutorId(tournamentReservationDTO.getTutorId());
            this.setTournamentId(tournamentReservationDTO.getTournamentId());
        }
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
