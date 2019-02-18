package pl.put.boardgamemanager.reservation.private_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PrivateReservationController {

    @Autowired
    private PrivateReservationService service;

    @GetMapping("/private_reservations/{id}")
    public PrivateReservationDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/private_reservations")
    public List<PrivateReservationDTO> all() {
        return service.all();
    }

    @PostMapping("/private_reservations")
    public void create(@RequestBody PrivateReservationDTO privateReservationDTO) {
        service.create(privateReservationDTO);
    }

    @PutMapping("/private_reservations")
    public PrivateReservationDTO update(@RequestBody PrivateReservationDTO privateReservationDTO) {
        return service.update(privateReservationDTO);
    }

    @DeleteMapping("/private_reservations/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
