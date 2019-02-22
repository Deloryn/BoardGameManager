package pl.put.boardgamemanager.tournament_participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentParticipantService {

    @Autowired
    private TournamentParticipantRepository tournamentParticipantRepository;

    public TournamentParticipantDTO get(Long clientId, Long tournamentId) {
        TournamentParticipant participant =
                tournamentParticipantRepository.findById(new TournamentParticipantPK(clientId, tournamentId)).orElse(null);

        if(participant == null) return null;
        else return participant.toDTO();
    }

    public List<TournamentParticipantDTO> all() {
        return tournamentParticipantRepository.findAll().stream()
                .map(TournamentParticipant::toDTO)
                .collect(Collectors.toList());
    }

    public TournamentParticipantDTO create(TournamentParticipantDTO dto) {
        TournamentParticipant participant = new TournamentParticipant();
        participant.updateParamsFrom(dto);
        tournamentParticipantRepository.save(participant);
        return participant.toDTO();
    }

    @Transactional
    public void delete(Long clientId, Long tournamentId) {
        tournamentParticipantRepository.deleteById(new TournamentParticipantPK(clientId, tournamentId));
    }

}
