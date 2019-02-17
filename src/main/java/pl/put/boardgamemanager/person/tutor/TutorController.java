package pl.put.boardgamemanager.person.tutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TutorController {

    @Autowired
    private TutorRepository repository;

    @GetMapping("/tutors/{id}")
    public TutorDTO get(@PathVariable Long id) {
        Tutor tutor = repository.findById(id).orElse(null);
        return Tutor.toDTO(tutor);
    }

    @GetMapping("/tutors")
    public List<TutorDTO> all() {
        List<TutorDTO> dtos = new ArrayList<>();
        repository.findAll().forEach(tutor -> {
            dtos.add(Tutor.toDTO(tutor));
        });
        return dtos;
    }

    @PostMapping("/tutors")
    public void create(@RequestBody TutorDTO tutorDTO) {
        repository.save(Tutor.fromDTO(tutorDTO));
    }

    @PutMapping("/tutors")
    public TutorDTO update(@RequestBody TutorDTO tutorDTO) {
        Tutor tutor = Tutor.fromDTO(tutorDTO);
        return repository.findById(tutor.getId())
                .map(currentTutor -> {
                    currentTutor.updateParams(currentTutor);
                    repository.save(currentTutor);
                    return Tutor.toDTO(currentTutor);
                })
                .orElseGet(() -> {
                    repository.save(tutor);
                    return Tutor.toDTO(tutor);
                });
    }

    @DeleteMapping("/tutors/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}

