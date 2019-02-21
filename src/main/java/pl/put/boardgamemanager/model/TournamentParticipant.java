package pl.put.boardgamemanager.model;

import pl.put.boardgamemanager.dto.TournamentParticipantDTO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "tournamentparticipants", schema = "public", catalog = "postgres")
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

    public void updateParamsFrom(TournamentParticipantDTO dto) {
        this.setPrimaryKey(new TournamentParticipantPK(dto.getClientId(), dto.getTournamentId()));
    }

    public TournamentParticipantDTO toDTO() {
        TournamentParticipantDTO dto = new TournamentParticipantDTO();

        dto.setClientId(primaryKey.getClientId());
        dto.setTournamentId(primaryKey.getTournamentId());

        return dto;
    }

}
