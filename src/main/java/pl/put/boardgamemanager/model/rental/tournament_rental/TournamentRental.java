package pl.put.boardgamemanager.model.rental.tournament_rental;

import pl.put.boardgamemanager.dto.DTO;
import pl.put.boardgamemanager.dto.TournamentRentalDTO;
import pl.put.boardgamemanager.model.Model;
import pl.put.boardgamemanager.model.rental.Rental;

import javax.persistence.*;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "tournamentrentals", schema = "public", catalog = "postgres")
@DiscriminatorValue("t")
public class TournamentRental extends Rental implements Model {

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
            TournamentRental that = (TournamentRental) o;
            return Objects.equals(tournamentId, that.tournamentId);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, copyId, tournamentId);
    }

    public void updateParamsFrom(DTO dto) {
        if(dto instanceof TournamentRentalDTO) {
            TournamentRentalDTO tournamentRentalDTO = (TournamentRentalDTO) dto;
            this.setCopyId(tournamentRentalDTO.getCopyId());
            this.setTournamentId(tournamentRentalDTO.getTournamentId());
        }
    }

    public TournamentRentalDTO toDTO() {
        TournamentRentalDTO dto = new TournamentRentalDTO();

        dto.setId(id);
        dto.setCopyId(copyId);
        dto.setTournamentId(tournamentId);

        return dto;
    }

}
