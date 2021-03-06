package pl.put.boardgamemanager.tournament_participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.boardgamemanager.ListDTO;

import java.util.List;

@RestController
@CrossOrigin
public class TournamentParticipantController {

    @Autowired
    private TournamentParticipantService service;

    @GetMapping("/tournament_participants/{clientId}/{tournamentId}")
    public TournamentParticipantDTO get(@PathVariable Long clientId, @PathVariable Long tournamentId) {
        return service.get(clientId, tournamentId);
    }

    @GetMapping("/tournament_participants")
    public ListDTO<TournamentParticipantDTO> all() {
        return service.all();
    }

    @PostMapping("/tournament_participants")
    public TournamentParticipantDTO create(@RequestBody TournamentParticipantDTO tournamentParticipantDTO) {
        if(!tournamentParticipantDTO.validate()) return tournamentParticipantDTO;
        return service.create(tournamentParticipantDTO);
    }

    @DeleteMapping("/tournament_participants/{clientId}/{tournamentId}")
    public void delete(@PathVariable Long clientId, @PathVariable Long tournamentId) {
        service.delete(clientId, tournamentId);
    }

}
