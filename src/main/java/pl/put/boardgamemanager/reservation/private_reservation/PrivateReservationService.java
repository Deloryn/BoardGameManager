package pl.put.boardgamemanager.reservation.private_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivateReservationService {

    @Autowired
    private PrivateReservationRepository repository;

    public PrivateReservationDTO get(Long id) {
        PrivateReservation privateReservation = repository.findById(id).orElse(null);
        return PrivateReservation.toDTO(privateReservation);
    }

    public List<PrivateReservationDTO> all() {
        return repository.findAll().stream()
                .map(PrivateReservation::toDTO)
                .collect(Collectors.toList());
    }

    public void create(PrivateReservationDTO privateReservationDTO) {
        repository.save(PrivateReservation.fromDTO(privateReservationDTO));
    }

    public PrivateReservationDTO update(PrivateReservationDTO privateReservationDTO) {
        PrivateReservation privateReservation = PrivateReservation.fromDTO(privateReservationDTO);
        return repository.findById(privateReservation.getTableId())
                .map(currentPrivateReservation -> {
                    currentPrivateReservation.updateParams(privateReservation);
                    repository.save(currentPrivateReservation);
                    return PrivateReservation.toDTO(currentPrivateReservation);
                })
                .orElseGet(() -> {
                    create(privateReservationDTO);
                    return privateReservationDTO;
                });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
