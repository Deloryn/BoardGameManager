package pl.put.boardgamemanager.person.tutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
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
    public void create(@RequestBody TutorDTO tutorDTO) {
        service.create(tutorDTO);
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
