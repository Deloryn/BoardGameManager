package pl.put.boardgamemanager.reservation.private_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.person.tutor.Tutor;
import pl.put.boardgamemanager.person.tutor.TutorDTO;
import pl.put.boardgamemanager.person.tutor.TutorRepository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivateReservationService {

    @Autowired
    private PrivateReservationRepository privateReservationRepository;

    @Autowired
    private TutorRepository tutorRepository;

    private List<Tutor> getTutorsFor(Long clientId, Timestamp reservationTime, Integer duration) {
        return privateReservationRepository
                .findAllByClientIdAndReservationTimeAndDuration(clientId, reservationTime, duration)
                .stream()
                .map(PrivateReservation::getTutorId)
                .map(tutorId -> tutorRepository.findById(tutorId).orElse(null))
                .collect(Collectors.toList());
    }

    public List<PrivateReservationDTO> assignTutorFor(Long clientId, Timestamp reservationTime, Integer duration, Long tutorId) {
        List<PrivateReservation> chosenReservations = privateReservationRepository
                .findAllByClientIdAndReservationTimeAndDuration(clientId, reservationTime, duration);

        chosenReservations.forEach(reservation -> {
            reservation.setTutorId(tutorId);
            privateReservationRepository.save(reservation);
        });

        return chosenReservations
                .stream()
                .map(PrivateReservation::toDTO)
                .collect(Collectors.toList());
    }

    public List<PrivateReservationDTO> deleteTutorFrom(Long clientId, Timestamp reservationTime, Integer duration) {
        List<PrivateReservation> chosenReservations = privateReservationRepository
                .findAllByClientIdAndReservationTimeAndDuration(clientId, reservationTime, duration);

        chosenReservations.forEach(reservation -> {
            reservation.setTutorId(null);
            privateReservationRepository.save(reservation);
        });

        return chosenReservations
                .stream()
                .map(PrivateReservation::toDTO)
                .collect(Collectors.toList());
    }

    public PrivateReservationDTO get(Long id) {
        PrivateReservation reservation = privateReservationRepository.findById(id).orElse(null);
        if (reservation == null) return null;
        else return reservation.toDTO();
    }

    public List<TutorDTO> getTutorDTOsFor(Long clientId, Timestamp reservationTime, Integer duration) {
        return getTutorsFor(clientId, reservationTime, duration)
                .stream()
                .map(Tutor::toDTO)
                .collect(Collectors.toList());
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
