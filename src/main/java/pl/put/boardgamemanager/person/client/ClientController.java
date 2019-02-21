package pl.put.boardgamemanager.person.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.boardgamemanager.private_rental.PrivateRentalDTO;

import java.util.List;

@RestController
@CrossOrigin
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping("/clients/{id}")
    public ClientDTO get(@PathVariable Long id) {
        return service.getClientDTOById(id);
    }

    @GetMapping("/clients/{id}/exists")
    public Boolean exists(@PathVariable Long id) {
        return service.exists(id);
    }

    @GetMapping("/clients/{id}/participated-tournaments")
    public List<ClientTournamentDTO> getParticipatedTournamentDTOs(@PathVariable Long id) {
        return service.getParticipatedTournamentDTOs(id);
    }

    @GetMapping("/clients/{id}/available-tournaments")
    public List<ClientTournamentDTO> getAvailableTournamentDTOs(@PathVariable Long id) {
        return service.getAvailableTournamentDTOs(id);
    }

    @GetMapping("/clients/{id}/reservations")
    public List<ClientReservationDTO> getClientReservationDTOs(@PathVariable Long id) {
        return service.getClientReservationDTOs(id);
    }

    @GetMapping("/clients/{id}/rentals")
    public List<PrivateRentalDTO> getRentalDTOs(@PathVariable Long id) {
        return service.getRentalDTOs(id);
    }

    @GetMapping("/clients")
    public List<ClientDTO> all() {
        return service.all();
    }

    @PostMapping("/clients/get-by-email")
    public ClientDTO getByEmail(@RequestBody ClientDTO clientDTO) {
        return service.getClientDTOByEmail(clientDTO.getEmail());
    }

    @PostMapping("/clients")
    public ClientDTO create(@RequestBody ClientDTO clientDTO) {
        return service.create(clientDTO);
    }

    @PutMapping("/clients")
    public ClientDTO update(@RequestBody ClientDTO clientDTO) {
        return service.update(clientDTO);
    }

    @DeleteMapping("/clients/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
