package pl.put.boardgamemanager.reservation.private_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivateReservationService {

    @Autowired
    private PrivateReservationRepository repository;

    public PrivateReservationDTO get(Long id) {
        PrivateReservation reservation = repository.findById(id).orElse(null);
        if(reservation == null) return null;
        else return reservation.toDTO();
    }

    public List<PrivateReservationDTO> all() {
        return repository.findAll().stream()
                .map(PrivateReservation::toDTO)
                .collect(Collectors.toList());
    }

    public PrivateReservationDTO create(PrivateReservationDTO dto) {
        PrivateReservation reservation = new PrivateReservation();
        reservation.updateParamsFrom(dto);
        repository.save(reservation);
        return reservation.toDTO();
    }

    public PrivateReservationDTO update(PrivateReservationDTO dto) {
        return repository.findById(dto.getTableId())
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
