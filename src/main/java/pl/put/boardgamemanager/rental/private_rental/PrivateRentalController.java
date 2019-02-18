package pl.put.boardgamemanager.rental.private_rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PrivateRentalController {

    @Autowired
    private PrivateRentalRepository repository;

    @GetMapping("/private_rentals/{id}")
    public PrivateRentalDTO get(@PathVariable Long id) {
        PrivateRental privateRental = repository.findById(id).orElse(null);
        return PrivateRental.toDTO(privateRental);
    }

    @GetMapping("/private_rentals")
    public List<PrivateRentalDTO> all() {
        List<PrivateRentalDTO> dtos = new ArrayList<>();
        repository.findAll().forEach(privateRental -> {
            dtos.add(PrivateRental.toDTO(privateRental));
        });
        return dtos;
    }

    @PostMapping("/private_rentals")
    public void create(@RequestBody PrivateRentalDTO privateRentalDTO) {
        repository.save(PrivateRental.fromDTO(privateRentalDTO));
    }

    @PutMapping("/private_rentals")
    public PrivateRentalDTO update(@RequestBody PrivateRentalDTO privateRentalDTO) {
        PrivateRental privateRental = PrivateRental.fromDTO(privateRentalDTO);
        return repository.findById(privateRental.getCopyId())
                .map(currentPrivateRental -> {
                    currentPrivateRental.updateParams(currentPrivateRental);
                    repository.save(currentPrivateRental);
                    return PrivateRental.toDTO(currentPrivateRental);
                })
                .orElseGet(() -> {
                    repository.save(privateRental);
                    return PrivateRental.toDTO(privateRental);
                });
    }

    @DeleteMapping("/private_rentals/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}