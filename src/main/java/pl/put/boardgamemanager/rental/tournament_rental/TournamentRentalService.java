package pl.put.boardgamemanager.rental.tournament_rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentRentalService {

    @Autowired
    private TournamentRentalRepository repository;

    public TournamentRentalDTO get(Long id) {
        TournamentRental rental = repository.findById(id).orElse(null);
        if(rental == null) return null;
        else return rental.toDTO();
    }

    public List<TournamentRentalDTO> all() {
        return repository.findAll().stream()
                .map(TournamentRental::toDTO)
                .collect(Collectors.toList());
    }

    public TournamentRentalDTO create(TournamentRentalDTO dto) {
        TournamentRental rental = new TournamentRental();
        rental.updateParamsFrom(dto);
        repository.save(rental);
        return rental.toDTO();
    }

    public TournamentRentalDTO update(TournamentRentalDTO dto) {
        return repository.findById(dto.getCopyId())
                .map(existingRental -> {
                    existingRental.updateParamsFrom(dto);
                    repository.save(existingRental);
                    return existingRental.toDTO();
                })
                .orElseGet(() -> create(dto));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
