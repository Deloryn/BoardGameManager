package pl.put.boardgamemanager.tournament_rental;

import pl.put.boardgamemanager.DTO;

public class TournamentRentalDTO extends DTO {

    private Long id;

    private Long copyId;

    private Long tournamentId;

    private String readOnlyGameName;

    public boolean validate() {
        if(copyId == null || tournamentId == null) {
            this.setErrorMessage("Neither copyId nor tournamentId can be null");
            return false;
        }
        return true;
    }

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
