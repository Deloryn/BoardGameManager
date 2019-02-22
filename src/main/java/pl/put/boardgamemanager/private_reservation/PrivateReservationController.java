package pl.put.boardgamemanager.private_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.boardgamemanager.person.tutor.TutorDTO;

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

    @GetMapping("/private_reservations/{id}/available-tutors")
    public List<TutorDTO> getAvailableTutorsFor(@PathVariable Long id) {
        return service.getAvailableTutorsFor(id);
    }

    @GetMapping("/private_reservations")
    public List<PrivateReservationDTO> all() {
        return service.all();
    }

    @PostMapping("/private_reservations")
    public PrivateReservationDTO create(@RequestBody PrivateReservationDTO privateReservationDTO) {
        return service.create(privateReservationDTO);
    }

    @GetMapping("/private_reservations/{id}/get-tutor")
    public TutorDTO getTutorFor(@PathVariable Long id) {
        return service.getTutorDTOFor(id);
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
