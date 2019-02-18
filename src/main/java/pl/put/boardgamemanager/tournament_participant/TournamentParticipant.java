package pl.put.boardgamemanager.tournament_participant;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tournamentparticipants", schema = "public", catalog = "postgres")
public class TournamentParticipant {

    @EmbeddedId
    private TournamentParticipantPK primaryKey;

    public TournamentParticipantPK getPrimaryKey() { return this.primaryKey; }

    public void setPrimaryKey(TournamentParticipantPK primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o)) return false;
        else if (getClass() != o.getClass()) return false;
        else {
            TournamentParticipant that = (TournamentParticipant) o;
            return Objects.equals(primaryKey, that.primaryKey);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(primaryKey);
    }

    public void updateParams(TournamentParticipant tournamentParticipant) {
        this.setPrimaryKey(tournamentParticipant.getPrimaryKey());
    }

    public static TournamentParticipant fromDTO(TournamentParticipantDTO dto) {

        if(dto == null) return null;

        TournamentParticipantPK primaryKey = new TournamentParticipantPK(dto.getClientId(), dto.getTournamentId());

        TournamentParticipant tournamentParticipant = new TournamentParticipant();
        tournamentParticipant.setPrimaryKey(primaryKey);

        return tournamentParticipant;
    }

    public static TournamentParticipantDTO toDTO(TournamentParticipant tournamentParticipant) {

        if(tournamentParticipant == null) return null;

        TournamentParticipantDTO dto = new TournamentParticipantDTO();

        dto.setClientId(tournamentParticipant.getPrimaryKey().getClientId());
        dto.setTournamentId(tournamentParticipant.getPrimaryKey().getTournamentId());

        return dto;
    }

}
