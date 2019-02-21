package pl.put.boardgamemanager.logic.reservation.private_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.model.person.tutor.Tutor;
import pl.put.boardgamemanager.dto.TutorDTO;
import pl.put.boardgamemanager.logic.person.tutor.TutorRepository;
import pl.put.boardgamemanager.model.reservation.private_reservation.PrivateReservation;
import pl.put.boardgamemanager.dto.PrivateReservationDTO;
import pl.put.boardgamemanager.util.Utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivateReservationService {

    @Autowired
    private PrivateReservationRepository privateReservationRepository;

    @Autowired
    private TutorRepository tutorRepository;

    private List<Tutor> getBusyTutorsForId(Long id) {
        PrivateReservation desiredReservation = privateReservationRepository.findById(id).orElse(null);
        if (desiredReservation == null) return null;
        else {
            return privateReservationRepository
                    .findAll()
                    .stream()
                    .filter(reservation -> Utils.isEventDuringAnother(reservation, desiredReservation))
                    .map(PrivateReservation::getTutorId)
                    .map(tutorId -> tutorRepository.findById(tutorId).orElse(null))
                    .collect(Collectors.toList());
        }
    }

    public PrivateReservationDTO assignTutorFor(Long id, Long tutorId) {
        PrivateReservation reservation = privateReservationRepository.findById(id).orElse(null);
        if (reservation == null) return null;
        else {
            reservation.setTutorId(tutorId);
            privateReservationRepository.save(reservation);
            return reservation.toDTO();
        }
    }

    public PrivateReservationDTO deleteTutorFrom(Long id) {
        PrivateReservation reservation = privateReservationRepository.findById(id).orElse(null);
        if (reservation == null) return null;
        else {
            reservation.setTutorId(null);
            privateReservationRepository.save(reservation);
            return reservation.toDTO();
        }
    }

    public PrivateReservationDTO get(Long id) {
        PrivateReservation reservation = privateReservationRepository.findById(id).orElse(null);
        if (reservation == null) return null;
        else return reservation.toDTO();
    }

    public TutorDTO getTutorDTOFor(Long id) {
        PrivateReservation reservation = privateReservationRepository.findById(id).orElse(null);
        if (reservation == null) return null;
        else {
            Tutor tutor = tutorRepository.findById(reservation.getTutorId()).orElse(null);
            if (tutor == null) return null;
            else return tutor.toDTO();
        }
    }

    public List<TutorDTO> getAvailableTutorsFor(Long id) {
        PrivateReservation desiredReservation = privateReservationRepository.findById(id).orElse(null);
        if (desiredReservation == null) return null;

        List<Tutor> busyTutors = getBusyTutorsForId(id);
        if (busyTutors == null) return null;
        else {
            List<Tutor> allTutors = tutorRepository.findAll();
            allTutors.removeAll(busyTutors);

            return allTutors
                    .stream()
                    .map(Tutor::toDTO)
                    .collect(Collectors.toList());
        }
    }

    public List<PrivateReservationDTO> all() {
        return privateReservationRepository.findAll().stream()
                .map(PrivateReservation::toDTO)
                .collect(Collectors.toList());
    }

    public PrivateReservationDTO create(PrivateReservationDTO dto) {
        PrivateReservation reservation = new PrivateReservation();
        reservation.updateParamsFrom(dto);
        privateReservationRepository.save(reservation);
        return reservation.toDTO();
    }

    public PrivateReservationDTO update(PrivateReservationDTO dto) {
        return privateReservationRepository.findById(dto.getTableId())
                .map(existingReservation -> {
                    existingReservation.updateParamsFrom(dto);
                    privateReservationRepository.save(existingReservation);
                    return existingReservation.toDTO();
                })
                .orElseGet(() -> create(dto));
    }

    public void delete(Long id) {
        privateReservationRepository.deleteById(id);
    }

}
