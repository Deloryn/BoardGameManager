package pl.put.boardgamemanager.tournament_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentReservationService {

    @Autowired
    private TournamentReservationRepository repository;

    public TournamentReservationDTO get(Long id) {
        TournamentReservation reservation = repository.findById(id).orElse(null);
        if(reservation == null) return null;
        else return reservation.toDTO();
    }

    public List<TournamentReservationDTO> all() {
        return repository.findAll().stream()
                .map(TournamentReservation::toDTO)
                .collect(Collectors.toList());
    }

    public TournamentReservationDTO create(TournamentReservationDTO dto) {
        TournamentReservation reservation = new TournamentReservation();
        reservation.updateParamsFrom(dto);
        repository.save(reservation);
        return reservation.toDTO();
    }

    public TournamentReservationDTO update(TournamentReservationDTO dto) {
        if(dto.getId() == null) return null;
        return repository.findById(dto.getId())
                .map(existingReservation -> {
                    existingReservation.updateParamsFrom(dto);
                    repository.save(existingReservation);
                    return existingReservation.toDTO();
                })
                .orElseGet(() -> create(dto));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
