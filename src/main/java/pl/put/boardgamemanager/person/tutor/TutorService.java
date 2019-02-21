package pl.put.boardgamemanager.person.tutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    public TutorDTO get(Long id) {
        Tutor tutor = tutorRepository.findById(id).orElse(null);
        if (tutor == null) return null;
        else return tutor.toDTO();
    }


    public List<TutorDTO> all() {
        return tutorRepository.findAll().stream()
                .map(Tutor::toDTO)
                .collect(Collectors.toList());
    }

    public TutorDTO create(TutorDTO dto) {
        Tutor tutor = new Tutor();
        tutor.updateParamsFrom(dto);
        tutorRepository.save(tutor);
        return tutor.toDTO();
    }

    public TutorDTO update(TutorDTO dto) {
        return tutorRepository.findById(dto.getId())
                .map(existingTutor -> {
                    existingTutor.updateParamsFrom(dto);
                    tutorRepository.save(existingTutor);
                    return existingTutor.toDTO();
                })
                .orElseGet(() -> create(dto));
    }

    public void delete(Long id) {
        tutorRepository.deleteById(id);
    }


}
