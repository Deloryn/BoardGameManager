package pl.put.boardgamemanager.rest.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.boardgamemanager.logic.person.tutor.TutorService;
import pl.put.boardgamemanager.dto.TutorDTO;

import java.util.List;

@RestController
@CrossOrigin
public class TutorController {

    @Autowired
    private TutorService service;

    @GetMapping("/tutors/{id}")
    public TutorDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/tutors")
    public List<TutorDTO> all() {
        return service.all();
    }

    @PostMapping("/tutors")
    public TutorDTO create(@RequestBody TutorDTO tutorDTO) {
        return service.create(tutorDTO);
    }

    @PutMapping("/tutors")
    public TutorDTO update(@RequestBody TutorDTO tutorDTO) {
        return service.update(tutorDTO);
    }

    @DeleteMapping("/tutors/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
