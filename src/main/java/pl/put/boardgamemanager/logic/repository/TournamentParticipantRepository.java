package pl.put.boardgamemanager.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.boardgamemanager.model.TournamentParticipant;
import pl.put.boardgamemanager.model.TournamentParticipantPK;

public interface TournamentParticipantRepository extends JpaRepository<TournamentParticipant, TournamentParticipantPK> {
    TournamentParticipant findByPrimaryKey(TournamentParticipantPK primaryKey);
    void deleteByPrimaryKey(TournamentParticipantPK primaryKey);
}
