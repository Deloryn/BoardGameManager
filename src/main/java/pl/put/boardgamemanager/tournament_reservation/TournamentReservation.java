package pl.put.boardgamemanager.tournament_reservation;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tournamentreservations", schema = "public", catalog = "postgres")
public class TournamentReservation {

    @SequenceGenerator(name = "tournamentreservations_seq", sequenceName = "tournamentreservations_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tournamentreservations_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tableid", nullable = false)
    private Long tableId;

    @Column(name = "tutorid")
    private Long tutorId;

    @Column(name = "tournamentid", nullable = false)
    private Long tournamentId;

    public void setId(Long id) { this.id = id; }

    public Long getId() { return id; }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getTutorId() { return tutorId; }

    public void setTutorId(Long tutorId) { this.tutorId = tutorId; }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TournamentReservation that = (TournamentReservation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(tableId, that.tableId) &&
                Objects.equals(tutorId, that.tutorId) &&
                Objects.equals(tournamentId, that.tournamentId);
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
