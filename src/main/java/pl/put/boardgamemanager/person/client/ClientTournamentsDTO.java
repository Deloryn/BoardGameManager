package pl.put.boardgamemanager.person.client;

import pl.put.boardgamemanager.tournament.TournamentDTO;

public class ClientTournamentsDTO {

    private TournamentDTO tournamentDTO;

    private String gameName;

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
