package pl.put.boardgamemanager.logic.tournament_participant;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.boardgamemanager.model.tournament_participant.TournamentParticipant;
import pl.put.boardgamemanager.model.tournament_participant.TournamentParticipantPK;

public interface TournamentParticipantRepository extends JpaRepository<TournamentParticipant, TournamentParticipantPK> {
    TournamentParticipant findByPrimaryKey(TournamentParticipantPK primaryKey);
    void deleteByPrimaryKey(TournamentParticipantPK primaryKey);
}
