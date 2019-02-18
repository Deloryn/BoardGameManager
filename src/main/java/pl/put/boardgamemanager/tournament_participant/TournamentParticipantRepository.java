package pl.put.boardgamemanager.tournament_participant;

import org.springframework.data.repository.CrudRepository;

public interface TournamentParticipantRepository extends CrudRepository<TournamentParticipant, TournamentParticipantPK> {
    TournamentParticipant findByPrimaryKey(TournamentParticipantPK primaryKey);
    void deleteByPrimaryKey(TournamentParticipantPK primaryKey);
}
