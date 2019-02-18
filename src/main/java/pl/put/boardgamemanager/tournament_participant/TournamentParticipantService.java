package pl.put.boardgamemanager.tournament_participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentParticipantService {

    @Autowired
    private TournamentParticipantRepository repository;

    public TournamentParticipantDTO get(Long clientId, Long tournamentId) {
        TournamentParticipant participant =
                repository.findByPrimaryKey(new TournamentParticipantPK(clientId, tournamentId));

        if(participant == null) return null;
        else return participant.toDTO();
    }

    public List<TournamentParticipantDTO> all() {
        return repository.findAll().stream()
                .map(TournamentParticipant::toDTO)
                .collect(Collectors.toList());
    }

    public TournamentParticipantDTO create(TournamentParticipantDTO dto) {
        TournamentParticipant participant = new TournamentParticipant();
        participant.updateParamsFrom(dto);
        repository.save(participant);
        return participant.toDTO();
    }

    public void delete(Long clientId, Long tournamentId) {
        repository.deleteByPrimaryKey(new TournamentParticipantPK(clientId, tournamentId));
    }

}
