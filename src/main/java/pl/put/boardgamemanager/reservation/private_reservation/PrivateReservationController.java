package pl.put.boardgamemanager.reservation.private_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PrivateReservationController {

    @Autowired
    private PrivateReservationRepository repository;

    @GetMapping("/private_reservations/{id}")
    public PrivateReservationDTO get(@PathVariable Long id) {
        PrivateReservation privateReservation = repository.findById(id).orElse(null);
        return PrivateReservation.toDTO(privateReservation);
    }

    @GetMapping("/private_reservations")
    public List<PrivateReservationDTO> all() {
        List<PrivateReservationDTO> dtos = new ArrayList<>();
        repository.findAll().forEach(privateReservation -> {
            dtos.add(PrivateReservation.toDTO(privateReservation));
        });
        return dtos;
    }

    @PostMapping("/private_reservations")
    public void create(@RequestBody PrivateReservationDTO privateReservationDTO) {
        repository.save(PrivateReservation.fromDTO(privateReservationDTO));
    }

    @PutMapping("/private_reservations")
    public PrivateReservationDTO update(@RequestBody PrivateReservationDTO privateReservationDTO) {
        PrivateReservation privateReservation = PrivateReservation.fromDTO(privateReservationDTO);
        return repository.findById(privateReservation.getTableId())
                .map(currentPrivateReservation -> {
                    currentPrivateReservation.updateParams(privateReservation);
                    repository.save(currentPrivateReservation);
                    return PrivateReservation.toDTO(currentPrivateReservation);
                })
                .orElseGet(() -> {
                    repository.save(privateReservation);
                    return PrivateReservation.toDTO(privateReservation);
                });
    }

    @DeleteMapping("/private_reservations/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
