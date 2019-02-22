package pl.put.boardgamemanager.private_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.person.tutor.Tutor;
import pl.put.boardgamemanager.person.tutor.TutorDTO;
import pl.put.boardgamemanager.person.tutor.TutorRepository;
import pl.put.boardgamemanager.Utils;

import java.util.List;
import java.util.Objects;
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
                    .filter(Objects::nonNull)
                    .map(tutorId -> tutorRepository.findById(tutorId).orElse(null))
                    .collect(Collectors.toList());
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
        else if(reservation.getTutorId() == null) return null;
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
        return privateReservationRepository.findById(dto.getId())
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
