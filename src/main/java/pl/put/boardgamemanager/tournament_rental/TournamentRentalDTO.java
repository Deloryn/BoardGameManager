package pl.put.boardgamemanager.tournament_rental;

public class TournamentRentalDTO {

    private Long id;

    private Long copyId;

    private Long tournamentId;

    private String readOnlyGameName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCopyId(Long copyId) {
        this.copyId = copyId;
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

    public String getReadOnlyGameName() {
        return readOnlyGameName;
    }

    public void setReadOnlyGameName(String readOnlyGameName) {
        this.readOnlyGameName = readOnlyGameName;
    }
}
