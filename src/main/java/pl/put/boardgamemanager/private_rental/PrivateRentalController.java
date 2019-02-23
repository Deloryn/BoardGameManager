package pl.put.boardgamemanager.private_rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.boardgamemanager.ListDTO;

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
    public ListDTO<PrivateRentalDTO> all() {
        return service.all();
    }

    @PostMapping("/private_rentals")
    public PrivateRentalDTO create(@RequestBody PrivateRentalDTO privateRentalDTO) {
        if(!privateRentalDTO.validate()) return privateRentalDTO;
        else return service.create(privateRentalDTO);
    }

    @PutMapping("/private_rentals")
    public PrivateRentalDTO update(@RequestBody PrivateRentalDTO privateRentalDTO) {
        if(privateRentalDTO.getId() == null) {
            privateRentalDTO.setErrorMessage("Id in updating cannot be null");
            return privateRentalDTO;
        }
        if(!privateRentalDTO.validate()) return privateRentalDTO;
        else return service.update(privateRentalDTO);
    }

    @DeleteMapping("/private_rentals/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
