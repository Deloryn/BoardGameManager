package pl.put.boardgamemanager.person.tutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.boardgamemanager.ListDTO;

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
    public ListDTO<TutorDTO> all() {
        return service.all();
    }

    @PostMapping("/tutors")
    public TutorDTO create(@RequestBody TutorDTO tutorDTO) {
        if(!tutorDTO.validate()) return tutorDTO;
        else return service.create(tutorDTO);
    }

    @PutMapping("/tutors")
    public TutorDTO update(@RequestBody TutorDTO tutorDTO) {
        if(tutorDTO.getId() == null) {
            tutorDTO.setErrorMessage("Id in updating cannot be null");
            return tutorDTO;
        }
        if(!tutorDTO.validate()) return tutorDTO;
        else return service.update(tutorDTO);
    }

    @DeleteMapping("/tutors/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
