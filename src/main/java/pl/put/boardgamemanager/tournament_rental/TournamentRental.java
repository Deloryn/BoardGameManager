package pl.put.boardgamemanager.tournament_rental;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tournamentrentals", schema = "public", catalog = "postgres")
public class TournamentRental {

    @SequenceGenerator(name = "tournamentrentals_seq", sequenceName = "tournamentrentals_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tournamentrentals_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "copyid", nullable = false)
    private Long copyId;

    @Column(name = "tournamentid", nullable = false, unique = true)
    private Long tournamentId;

    public void setId(Long id) { this.id = id; }

    public Long getId() { return id; }

    public void setCopyId(Long copyid) {
        this.copyId = copyid;
    }

    public Long getCopyId() {
        return copyId;
    }

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
        TournamentRental that = (TournamentRental) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(copyId, that.copyId) &&
                Objects.equals(tournamentId, that.tournamentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, copyId, tournamentId);
    }

    public void updateParamsFrom(TournamentRentalDTO dto) {
        this.setCopyId(dto.getCopyId());
        this.setTournamentId(dto.getTournamentId());
    }

    public TournamentRentalDTO toDTO() {
        TournamentRentalDTO dto = new TournamentRentalDTO();

        dto.setId(id);
        dto.setCopyId(copyId);
        dto.setTournamentId(tournamentId);

        return dto;
    }

}
