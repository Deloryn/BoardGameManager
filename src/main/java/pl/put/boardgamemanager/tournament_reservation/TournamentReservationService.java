package pl.put.boardgamemanager.tournament_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.ListDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentReservationService {

    @Autowired
    private TournamentReservationRepository repository;

    public TournamentReservationDTO get(Long id) {
        TournamentReservation reservation = repository.findById(id).orElse(null);
        if(reservation == null) {
            TournamentReservationDTO dto = new TournamentReservationDTO();
            dto.setErrorMessage("There is no tournament reservation with the given id");
            return dto;
        }
        else return reservation.toDTO();
    }

    public ListDTO<TournamentReservationDTO> all() {
        ListDTO<TournamentReservationDTO> resultDTO = new ListDTO<>();
        resultDTO.setValues(repository.findAll().stream()
                .map(TournamentReservation::toDTO)
                .collect(Collectors.toList()));
        return resultDTO;
    }

    public TournamentReservationDTO create(TournamentReservationDTO dto) {
        TournamentReservation reservation = new TournamentReservation();
        reservation.updateParamsFrom(dto);
        repository.save(reservation);
        return reservation.toDTO();
    }

    public TournamentReservationDTO update(TournamentReservationDTO dto) {
        if(dto.getId() == null) return null;
        return repository.findById(dto.getId())
                .map(existingReservation -> {
                    existingReservation.updateParamsFrom(dto);
                    repository.save(existingReservation);
                    return existingReservation.toDTO();
                })
                .orElseGet(() -> create(dto));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
