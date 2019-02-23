package pl.put.boardgamemanager.person.tutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.ListDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    public TutorDTO get(Long id) {
        Tutor tutor = tutorRepository.findById(id).orElse(null);
        if (tutor == null) {
            TutorDTO dto = new TutorDTO();
            dto.setErrorMessage("There is no tutor with the given id");
            return dto;
        }
        else return tutor.toDTO();
    }


    public ListDTO<TutorDTO> all() {
        ListDTO<TutorDTO> resultDTO = new ListDTO<>();
        resultDTO.setValues(tutorRepository.findAll().stream()
                .map(Tutor::toDTO)
                .collect(Collectors.toList()));
        return resultDTO;
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
