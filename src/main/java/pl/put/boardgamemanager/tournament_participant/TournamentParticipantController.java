package pl.put.boardgamemanager.tournament_participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TournamentParticipantController {

    @Autowired
    private TournamentParticipantService service;

    @GetMapping("/tournament_participants/{clientId}/{tournamentId}")
    public TournamentParticipantDTO get(@PathVariable Long clientId, @PathVariable Long tournamentId) {
        return service.get(clientId, tournamentId);
    }

    @GetMapping("/tournament_participants")
    public List<TournamentParticipantDTO> all() {
        return service.all();
    }

    @PostMapping("/tournament_participants")
    public void create(@RequestBody TournamentParticipantDTO tournamentParticipantDTO) {
        service.create(tournamentParticipantDTO);
    }

    @PutMapping("/tournament_participants")
    public TournamentParticipantDTO update(@RequestBody TournamentParticipantDTO tournamentParticipantDTO) {
        return service.update(tournamentParticipantDTO);
    }

    @DeleteMapping("/tournament_participants/{clientId}/{tournamentId}")
    public void delete(@PathVariable Long clientId, @PathVariable Long tournamentId) {
        service.delete(clientId, tournamentId);
    }

}
