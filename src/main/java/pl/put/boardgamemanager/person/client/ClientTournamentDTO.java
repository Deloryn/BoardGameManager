package pl.put.boardgamemanager.person.client;

import pl.put.boardgamemanager.DTO;
import pl.put.boardgamemanager.tournament.TournamentDTO;

public class ClientTournamentDTO extends DTO {

    private TournamentDTO tournamentDTO;

    private String gameName;

    public boolean validate() {
        if(!tournamentDTO.validate()) {
            this.setErrorMessage(tournamentDTO.getErrorMessage());
            return false;
        }
        if(gameName == null) {
            this.setErrorMessage("Game name cannot be null");
            return false;
        }
        if(gameName.trim().isEmpty()) {
            this.setErrorMessage("Game name cannot be blank");
            return false;
        }
        return true;
    }

    public TournamentDTO getTournamentDTO() {
        return tournamentDTO;
    }

    public void setTournamentDTO(TournamentDTO tournamentDTO) {
        this.tournamentDTO = tournamentDTO;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

}
