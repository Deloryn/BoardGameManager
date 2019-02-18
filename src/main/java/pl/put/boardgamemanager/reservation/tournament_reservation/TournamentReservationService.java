package pl.put.boardgamemanager.reservation.tournament_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentReservationService {

    @Autowired
    private TournamentReservationRepository repository;

    public TournamentReservationDTO get(Long id) {
        TournamentReservation tournamentReservation = repository.findById(id).orElse(null);
        return TournamentReservation.toDTO(tournamentReservation);
    }

    public List<TournamentReservationDTO> all() {
        return repository.findAll().stream()
                .map(TournamentReservation::toDTO)
                .collect(Collectors.toList());
    }

    public void create(TournamentReservationDTO tournamentReservationDTO) {
        repository.save(TournamentReservation.fromDTO(tournamentReservationDTO));
    }

    public TournamentReservationDTO update(TournamentReservationDTO tournamentReservationDTO) {
        TournamentReservation tournamentReservation = TournamentReservation.fromDTO(tournamentReservationDTO);
        return repository.findById(tournamentReservation.getTableId())
                .map(currentTournamentReservation -> {
                    currentTournamentReservation.updateParams(tournamentReservation);
                    repository.save(currentTournamentReservation);
                    return TournamentReservation.toDTO(currentTournamentReservation);
                })
                .orElseGet(() -> {
                    create(tournamentReservationDTO);
                    return tournamentReservationDTO;
                });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
