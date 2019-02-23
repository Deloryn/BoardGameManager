package pl.put.boardgamemanager.tournament_participant;

import pl.put.boardgamemanager.DTO;

public class TournamentParticipantDTO extends DTO {

    private Long clientId;

    private Long tournamentId;

    public boolean validate() {
        if(clientId == null || tournamentId == null) {
            this.setErrorMessage("Neither clientId nor tournamentId can be null");
            return false;
        }
        return true;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }
}
