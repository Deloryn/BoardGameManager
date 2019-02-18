package pl.put.boardgamemanager.tournament_participant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentParticipantRepository extends JpaRepository<TournamentParticipant, TournamentParticipantPK> {
    TournamentParticipant findByPrimaryKey(TournamentParticipantPK primaryKey);
    void deleteByPrimaryKey(TournamentParticipantPK primaryKey);
}
