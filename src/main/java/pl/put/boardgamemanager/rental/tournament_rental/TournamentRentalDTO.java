package pl.put.boardgamemanager.rental.tournament_rental;

public class TournamentRentalDTO {

    private Long copyId;

    private Long tournamentId;

    public Long getCopyId() {
        return copyId;
    }

    public void setCopyId(Long copyId) {
        this.copyId = copyId;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

}
