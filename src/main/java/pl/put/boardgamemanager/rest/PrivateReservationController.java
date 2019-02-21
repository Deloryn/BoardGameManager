package pl.put.boardgamemanager.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.boardgamemanager.logic.PrivateReservationService;
import pl.put.boardgamemanager.dto.TutorDTO;
import pl.put.boardgamemanager.dto.PrivateReservationDTO;

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

    @PostMapping("/private_reservations/get-tutor")
    public TutorDTO getTutorFor(@RequestBody PrivateReservationDTO dto) {
        return service.getTutorDTOFor(dto.getId());
    }

    @PostMapping("/private_reservations/assign-tutor")
    public PrivateReservationDTO assignTutorFor(@RequestBody PrivateReservationDTO dto) {
        return service.assignTutorFor(dto.getId(), dto.getTutorId());
    }

    @PostMapping("/private_reservations/delete-tutor")
    public PrivateReservationDTO deleteTutorFrom(@RequestBody PrivateReservationDTO dto) {
        return service.deleteTutorFrom(dto.getId());
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
