package pl.put.boardgamemanager.tournament_participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TournamentParticipantController {

    @Autowired
    private TournamentParticipantRepository repository;

    @GetMapping("/tournament_participants/{clientId}/{tournamentId}")
    public TournamentParticipantDTO get(@PathVariable Long clientId, @PathVariable Long tournamentId) {
        TournamentParticipant tournamentParticipant =
                repository.findByPrimaryKey(new TournamentParticipantPK(clientId, tournamentId));
        return TournamentParticipant.toDTO(tournamentParticipant);
    }

    @GetMapping("/tournament_participants")
    public List<TournamentParticipantDTO> all() {
        List<TournamentParticipantDTO> dtos = new ArrayList<>();
        repository.findAll().forEach(tournamentParticipant -> {
            dtos.add(TournamentParticipant.toDTO(tournamentParticipant));
        });
        return dtos;
    }

    @PostMapping("/tournament_participants")
    public void create(@RequestBody TournamentParticipantDTO tournamentParticipantDTO) {
        repository.save(TournamentParticipant.fromDTO(tournamentParticipantDTO));
    }

    @PutMapping("/tournament_participants")
    public TournamentParticipantDTO update(@RequestBody TournamentParticipantDTO tournamentParticipantDTO) {
        TournamentParticipant tournamentParticipant = TournamentParticipant.fromDTO(tournamentParticipantDTO);
        return new Optional<>(repository.findByPrimaryKey(tournamentParticipant.getPrimaryKey()))
                .map(currentTournamentParticipant -> {
                    currentTournamentParticipant.updateParams(currentTournamentParticipant);
                    repository.save(currentTournamentParticipant);
                    return TournamentParticipant.toDTO(currentTournamentParticipant);
                })
                .orElseGet(() -> {
                    repository.save(tournamentParticipant);
                    return TournamentParticipant.toDTO(tournamentParticipant);
                });
    }

    @DeleteMapping("/tournament_participants/{clientId}/{tournamentId}")
    public void delete(@PathVariable Long clientId, @PathVariable Long tournamentId) {
        repository.deleteByPrimaryKey(new TournamentParticipantPK(clientId, tournamentId));
    }

}
