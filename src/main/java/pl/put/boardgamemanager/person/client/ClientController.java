package pl.put.boardgamemanager.person.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.boardgamemanager.ValueDTO;
import pl.put.boardgamemanager.ListDTO;
import pl.put.boardgamemanager.private_rental.PrivateRentalDTO;

@RestController
@CrossOrigin
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping("/clients/{id}")
    public ClientDTO get(@PathVariable Long id) {
        return service.getClientDTOById(id);
    }

    @GetMapping("/clients/regular-emails")
    public ListDTO<String> getRegularClientsEmails() {
        return service.getRegularClientsEmails();
    }

    @GetMapping("/clients/copy-persons-table")
    public void copyPersonsTable() {
        service.copyPersonsTable();
    }

    @GetMapping("/clients/{id}/exists")
    public ValueDTO<Boolean> exists(@PathVariable Long id) {
        return service.exists(id);
    }

    @GetMapping("/clients/{id}/participated-tournaments")
    public ListDTO<ClientTournamentDTO> getParticipatedTournamentDTOs(@PathVariable Long id) {
        return service.getParticipatedTournamentDTOs(id);
    }

    @GetMapping("/clients/{id}/available-tournaments")
    public ListDTO<ClientTournamentDTO> getAvailableTournamentDTOs(@PathVariable Long id) {
        return service.getAvailableTournamentDTOs(id);
    }

    @GetMapping("/clients/{id}/reservations")
    public ListDTO<ClientReservationDTO> getClientReservationDTOs(@PathVariable Long id) {
        return service.getClientReservationDTOs(id);
    }

    @GetMapping("/clients/{id}/rentals")
    public ListDTO<PrivateRentalDTO> getRentalDTOs(@PathVariable Long id) {
        return service.getRentalDTOs(id);
    }

    @GetMapping("/clients")
    public ListDTO<ClientDTO> all() {
        return service.all();
    }

    @PostMapping("/clients/get-by-email")
    public ClientDTO getByEmail(@RequestBody ValueDTO<String> valueDTO) {
        if(!valueDTO.validate()) {
            ClientDTO resultDTO = new ClientDTO();
            resultDTO.setErrorMessage(valueDTO.getErrorMessage());
            return resultDTO;
        }
        else return service.getClientDTOByEmail(valueDTO.getValue());
    }

    @PostMapping("/clients")
    public ClientDTO create(@RequestBody ClientDTO clientDTO) {
        if(!clientDTO.validate()) return clientDTO;
        else return service.create(clientDTO);
    }

    @PutMapping("/clients")
    public ClientDTO update(@RequestBody ClientDTO clientDTO) {
        if(clientDTO.getId() == null) {
            clientDTO.setErrorMessage("Id in updating cannot be null");
            return clientDTO;
        }
        if(!clientDTO.validate()) return clientDTO;
        else return service.update(clientDTO);
    }

    @DeleteMapping("/clients/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
