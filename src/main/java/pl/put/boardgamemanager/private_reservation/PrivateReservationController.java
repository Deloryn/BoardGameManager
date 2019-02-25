package pl.put.boardgamemanager.private_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.boardgamemanager.ListDTO;
import pl.put.boardgamemanager.TimeAndTargetDTO;
import pl.put.boardgamemanager.person.tutor.TutorDTO;

@RestController
@CrossOrigin
public class PrivateReservationController {

    @Autowired
    private PrivateReservationService service;

    @GetMapping("/private_reservations/{id}")
    public PrivateReservationDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping("/private_reservations/available-tutors-at-reservation")
    public ListDTO<TutorDTO> getAvailableTutorsAtReservation(@RequestBody TimeAndTargetDTO dto) {
        if(!dto.validate()) {
            ListDTO<TutorDTO> resultDTO = new ListDTO<>();
            resultDTO.setErrorMessage(dto.getErrorMessage());
            return resultDTO;
        }
        return service.getAvailableTutorsAt(dto.getStartTime(), dto.getDuration(), dto.getTargetId());
    }

    @GetMapping("/private_reservations")
    public ListDTO<PrivateReservationDTO> all() {
        return service.all();
    }

    @PostMapping("/private_reservations")
    public PrivateReservationDTO create(@RequestBody PrivateReservationDTO privateReservationDTO) {
        if(!privateReservationDTO.validate()) return privateReservationDTO;
        else return service.create(privateReservationDTO);
    }

    @GetMapping("/private_reservations/{id}/get-tutor")
    public TutorDTO getTutorFor(@PathVariable Long id) {
        return service.getTutorDTOFor(id);
    }

    @PutMapping("/private_reservations")
    public PrivateReservationDTO update(@RequestBody PrivateReservationDTO privateReservationDTO) {
        if(privateReservationDTO.getId() == null) {
            privateReservationDTO.setErrorMessage("Id in updating cannot be null");
            return privateReservationDTO;
        }
        if(!privateReservationDTO.validate()) return privateReservationDTO;
        else return service.update(privateReservationDTO);
    }

    @DeleteMapping("/private_reservations/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
