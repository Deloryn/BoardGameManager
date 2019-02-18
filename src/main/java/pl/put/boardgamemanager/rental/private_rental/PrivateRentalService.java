package pl.put.boardgamemanager.rental.private_rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivateRentalService {

    @Autowired
    private PrivateRentalRepository repository;

    public PrivateRentalDTO get(Long id) {
        PrivateRental privateRental = repository.findById(id).orElse(null);
        return PrivateRental.toDTO(privateRental);
    }

    public List<PrivateRentalDTO> all() {
        return repository.findAll().stream()
                .map(PrivateRental::toDTO)
                .collect(Collectors.toList());
    }

    public void create(PrivateRentalDTO privateRentalDTO) {
        repository.save(PrivateRental.fromDTO(privateRentalDTO));
    }

    public PrivateRentalDTO update(PrivateRentalDTO privateRentalDTO) {
        PrivateRental privateRental = PrivateRental.fromDTO(privateRentalDTO);
        return repository.findById(privateRental.getCopyId())
                .map(currentPrivateRental -> {
                    currentPrivateRental.updateParams(privateRental);
                    repository.save(currentPrivateRental);
                    return PrivateRental.toDTO(currentPrivateRental);
                })
                .orElseGet(() -> {
                    create(privateRentalDTO);
                    return privateRentalDTO;
                });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
