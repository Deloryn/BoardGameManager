package pl.put.boardgamemanager.person.tutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        return repository.findAll().stream()
                .map(Tutor::toDTO)
                .collect(Collectors.toList());
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
                    currentTutor.updateParams(tutor);
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
