package pl.put.boardgamemanager.person.tutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    public TutorDTO get(Long id) {
        Tutor tutor = repository.findById(id).orElse(null);
        return Tutor.toDTO(tutor);
    }

    public List<TutorDTO> all() {
        return repository.findAll().stream()
                .map(Tutor::toDTO)
                .collect(Collectors.toList());
    }

    public void create(TutorDTO tutorDTO) {
        repository.save(Tutor.fromDTO(tutorDTO));
    }

    public TutorDTO update(TutorDTO tutorDTO) {
        Tutor tutor = Tutor.fromDTO(tutorDTO);
        return repository.findById(tutor.getId())
                .map(currentTutor -> {
                    currentTutor.updateParams(tutor);
                    repository.save(currentTutor);
                    return Tutor.toDTO(currentTutor);
                })
                .orElseGet(() -> {
                    create(tutorDTO);
                    return tutorDTO;
                });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}
