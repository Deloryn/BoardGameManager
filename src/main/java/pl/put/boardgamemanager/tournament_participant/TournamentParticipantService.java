package pl.put.boardgamemanager.tournament_participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.ListDTO;

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

        if(participant == null) {
            TournamentParticipantDTO dto = new TournamentParticipantDTO();
            dto.setErrorMessage("There is no tournament participant for the given clientId and tournamentId");
            return dto;
        }
        else return participant.toDTO();
    }

    public ListDTO<TournamentParticipantDTO> all() {
        ListDTO<TournamentParticipantDTO> resultDTO = new ListDTO<>();
        resultDTO.setValues(tournamentParticipantRepository.findAll().stream()
                .map(TournamentParticipant::toDTO)
                .collect(Collectors.toList()));
        return resultDTO;
    }

    public TournamentParticipantDTO create(TournamentParticipantDTO dto) {
        TournamentParticipant participant = new TournamentParticipant();
        participant.updateParamsFrom(dto);

        try {
            tournamentParticipantRepository.save(participant);
            return participant.toDTO();
        }
        catch(DataIntegrityViolationException ex) {
            dto.setErrorMessage("Given data violates data constraints");
            return dto;
        }

    }

    @Transactional
    public void delete(Long clientId, Long tournamentId) {
        tournamentParticipantRepository.deleteById(new TournamentParticipantPK(clientId, tournamentId));
    }

}
