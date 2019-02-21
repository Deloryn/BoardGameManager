package pl.put.boardgamemanager.logic.rental.private_rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.model.rental.private_rental.PrivateRental;
import pl.put.boardgamemanager.dto.PrivateRentalDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivateRentalService {

    @Autowired
    private PrivateRentalRepository repository;

    public PrivateRentalDTO get(Long id) {
        PrivateRental rental = repository.findById(id).orElse(null);
        if(rental == null) return null;
        else return rental.toDTO();
    }

    public List<PrivateRentalDTO> all() {
        return repository.findAll().stream()
                .map(PrivateRental::toDTO)
                .collect(Collectors.toList());
    }

    public PrivateRentalDTO create(PrivateRentalDTO dto) {
        PrivateRental rental = new PrivateRental();
        rental.updateParamsFrom(dto);
        repository.save(rental);
        return rental.toDTO();
    }

    public PrivateRentalDTO update(PrivateRentalDTO dto) {
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
