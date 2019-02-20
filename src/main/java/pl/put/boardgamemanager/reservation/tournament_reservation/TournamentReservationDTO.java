package pl.put.boardgamemanager.reservation.tournament_reservation;

public class TournamentReservationDTO {

    private Long id;

    private Long tableId;

    private Long tutorId;

    private Long tournamentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getTableId() {
        return tableId;
    }

    public Long getTutorId() { return tutorId; }

    public void setTutorId(Long tutorId) { this.tutorId = tutorId; }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

}
