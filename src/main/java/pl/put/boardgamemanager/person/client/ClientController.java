package pl.put.boardgamemanager.person.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping("/clients/{id}")
    public ClientDTO get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/clients/{id}/exists")
    public Boolean exists(@PathVariable Long id) {
        return service.exists(id);
    }

    @GetMapping("/clients/{id}/participated-tournaments")
    public List<ClientTournamentsDTO> getParticipatedTournamentDTOs(@PathVariable Long id) {
        return service.getParticipatedTournamentDTOs(id);
    }

    @GetMapping("/clients/{id}/available-tournaments")
    public List<ClientTournamentsDTO> getAvailableTournamentDTOs(@PathVariable Long id) {
        return service.getAvailableTournamentDTOs(id);
    }

    @GetMapping("/clients/get-by-email")
    public ClientDTO getByEmail(@RequestBody ClientDTO clientDTO) {
        return service.getByEmail(clientDTO.getEmail());
    }

    @GetMapping("/clients")
    public List<ClientDTO> all() {
        return service.all();
    }

    @PostMapping("/clients")
    public void create(@RequestBody ClientDTO clientDTO) {
        service.create(clientDTO);
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
