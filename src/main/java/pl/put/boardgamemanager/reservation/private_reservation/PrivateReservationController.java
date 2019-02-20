package pl.put.boardgamemanager.reservation.private_reservation;

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

    @GetMapping("/private_reservations")
    public List<PrivateReservationDTO> all() {
        return service.all();
    }

    @PostMapping("/private_reservations")
    public PrivateReservationDTO create(@RequestBody PrivateReservationDTO privateReservationDTO) {
        return service.create(privateReservationDTO);
    }

    @PostMapping("/private_reservations/get-tutor")
    public List<TutorDTO> getTutorFor(@RequestBody PrivateReservationDTO dto) {
        return service.getTutorDTOsFor(dto.getClientId(), dto.getReservationTime(), dto.getDuration());
    }

    @PostMapping("/private_reservations/assign-tutor")
    public List<PrivateReservationDTO> assignTutorFor(@RequestBody PrivateReservationDTO dto) {
        return service.assignTutorFor(dto.getClientId(), dto.getReservationTime(), dto.getDuration(), dto.getTutorId());
    }

    @PostMapping("/private_reservations/delete-tutor")
    public List<PrivateReservationDTO> deleteTutorFrom(@RequestBody PrivateReservationDTO dto) {
        return service.deleteTutorFrom(dto.getClientId(), dto.getReservationTime(), dto.getDuration());
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
