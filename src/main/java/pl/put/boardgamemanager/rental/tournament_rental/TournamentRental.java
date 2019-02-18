package pl.put.boardgamemanager.rental.tournament_rental;

import pl.put.boardgamemanager.rental.Rental;
import pl.put.boardgamemanager.tournament.Tournament;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tournamentrentals", schema = "public", catalog = "postgres")
@DiscriminatorValue("t")
public class TournamentRental extends Rental {

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
        return Objects.hash(copyId, tournamentId);
    }

    public void updateParams(TournamentRental tournamentRental) {
        this.setTournamentId(tournamentRental.getTournamentId());
    }

    public static TournamentRental fromDTO(TournamentRentalDTO dto) {

        if(dto == null) return null;

        TournamentRental tournamentRental = new TournamentRental();

        tournamentRental.setCopyId(dto.getCopyId());
        tournamentRental.setTournamentId(dto.getTournamentId());

        return tournamentRental;
    }

    public static TournamentRentalDTO toDTO(TournamentRental tournamentRental) {

        if(tournamentRental == null) return null;

        TournamentRentalDTO dto = new TournamentRentalDTO();

        dto.setTournamentId(tournamentRental.getTournamentId());

        return dto;
    }

}
