package pl.put.boardgamemanager.private_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.ListDTO;
import pl.put.boardgamemanager.Utils;
import pl.put.boardgamemanager.person.tutor.Tutor;
import pl.put.boardgamemanager.person.tutor.TutorDTO;
import pl.put.boardgamemanager.person.tutor.TutorRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PrivateReservationService {

    @Autowired
    private PrivateReservationRepository privateReservationRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public PrivateReservationDTO get(Long id) {
        PrivateReservation reservation = privateReservationRepository.findById(id).orElse(null);
        if (reservation == null) {
            PrivateReservationDTO dto = new PrivateReservationDTO();
            dto.setErrorMessage("There is no private reservation with the given id");
            return dto;
        } else return reservation.toDTO();
    }

    public TutorDTO getTutorDTOFor(Long id) {
        PrivateReservation reservation = privateReservationRepository.findById(id).orElse(null);
        if (reservation == null) {
            TutorDTO resultDTO = new TutorDTO();
            resultDTO.setErrorMessage("There is no private reservation with the given id");
            return resultDTO;
        } else if (reservation.getTutorId() == null) return new TutorDTO();
        else {
            Tutor tutor = tutorRepository.findById(reservation.getTutorId()).orElse(null);
            if (tutor == null) return new TutorDTO();
            else return tutor.toDTO();
        }
    }

    public ListDTO<TutorDTO> getAvailableTutorsAt(LocalDateTime startTime, Integer duration, Long targetId) {
        ListDTO<TutorDTO> resultDTO = new ListDTO<>();

        List<Tutor> busyTutors = getBusyTutorsAt(startTime, duration, targetId);
        if (busyTutors.isEmpty())
            resultDTO.setValues(tutorRepository.findAll().stream().map(Tutor::toDTO).collect(Collectors.toList()));
        else {
            List<Tutor> allTutors = tutorRepository.findAll();
            allTutors.removeAll(busyTutors);

            resultDTO.setValues(allTutors
                    .stream()
                    .map(Tutor::toDTO)
                    .collect(Collectors.toList()));
        }
        return resultDTO;
    }

    public ListDTO<PrivateReservationDTO> all() {
        ListDTO<PrivateReservationDTO> resultDTO = new ListDTO<>();
        resultDTO.setValues(privateReservationRepository.findAll().stream()
                .map(PrivateReservation::toDTO)
                .collect(Collectors.toList()));
        return resultDTO;
    }

    public PrivateReservationDTO create(PrivateReservationDTO dto) {
        PrivateReservation reservation = new PrivateReservation();
        reservation.updateParamsFrom(dto);

        try {
            privateReservationRepository.save(reservation);
            return reservation.toDTO();
        }
        catch(DataIntegrityViolationException ex) {
            dto.setErrorMessage("Given data violates data constraints");
            return dto;
        }

    }

    public PrivateReservationDTO update(PrivateReservationDTO dto) {
        return privateReservationRepository.findById(dto.getId())
                .map(existingReservation -> {
                    existingReservation.updateParamsFrom(dto);
                    try {
                        privateReservationRepository.save(existingReservation);
                        return existingReservation.toDTO();
                    }
                    catch(DataIntegrityViolationException ex) {
                        dto.setErrorMessage("Given data violates data constraints");
                        return dto;
                    }
                })
                .orElseGet(() -> create(dto));
    }

    public void delete(Long id) {
        privateReservationRepository.deleteById(id);
    }

    private List<Tutor> getBusyTutorsAt(LocalDateTime startTime, Integer duration, Long targetId) {
        PrivateReservation desiredReservation = new PrivateReservation();
        desiredReservation.setStartTime(startTime);
        desiredReservation.setDuration(duration);

        return privateReservationRepository
                    .findAll()
                    .stream()
                    .filter(reservation -> !reservation.getId().equals(targetId))
                    .filter(reservation -> Utils.isEventDuringAnother(reservation, desiredReservation))
                    .map(PrivateReservation::getTutorId)
                    .filter(Objects::nonNull)
                    .map(tutorId -> tutorRepository.findById(tutorId).orElse(null))
                    .collect(Collectors.toList());
    }

}
