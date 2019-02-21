package pl.put.boardgamemanager.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.boardgamemanager.logic.PrivateRentalService;
import pl.put.boardgamemanager.dto.PrivateRentalDTO;

import java.util.List;

@RestController
@CrossOrigin
public class PrivateRentalController {

    @Autowired
    private PrivateRentalService service;

    @GetMapping("/private_rentals/{id}")
    public PrivateRentalDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/private_rentals")
    public List<PrivateRentalDTO> all() {
        return service.all();
    }

    @PostMapping("/private_rentals")
    public PrivateRentalDTO create(@RequestBody PrivateRentalDTO privateRentalDTO) {
        return service.create(privateRentalDTO);
    }

    @PutMapping("/private_rentals")
    public PrivateRentalDTO update(@RequestBody PrivateRentalDTO privateRentalDTO) {
        return service.update(privateRentalDTO);
    }

    @DeleteMapping("/private_rentals/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
