package pl.put.boardgamemanager.tournament_participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TournamentParticipantService {

    @Autowired
    private TournamentParticipantRepository repository;

    public TournamentParticipantDTO get(Long clientId, Long tournamentId) {
        TournamentParticipant tournamentParticipant =
                repository.findByPrimaryKey(new TournamentParticipantPK(clientId, tournamentId));
        return TournamentParticipant.toDTO(tournamentParticipant);
    }

    public List<TournamentParticipantDTO> all() {
        return repository.findAll().stream()
                .map(TournamentParticipant::toDTO)
                .collect(Collectors.toList());
    }

    public void create(TournamentParticipantDTO tournamentParticipantDTO) {
        repository.save(TournamentParticipant.fromDTO(tournamentParticipantDTO));
    }

    public TournamentParticipantDTO update(TournamentParticipantDTO tournamentParticipantDTO) {
        TournamentParticipant tournamentParticipant = TournamentParticipant.fromDTO(tournamentParticipantDTO);
        return Optional.of(repository.findByPrimaryKey(tournamentParticipant.getPrimaryKey()))
                .map(currentTournamentParticipant -> {
                    currentTournamentParticipant.updateParams(tournamentParticipant);
                    repository.save(currentTournamentParticipant);
                    return TournamentParticipant.toDTO(currentTournamentParticipant);
                })
                .orElseGet(() -> {
                    create(tournamentParticipantDTO);
                    return tournamentParticipantDTO;
                });
    }

    public void delete(Long clientId, Long tournamentId) {
        repository.deleteByPrimaryKey(new TournamentParticipantPK(clientId, tournamentId));
    }

}
