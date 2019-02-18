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
        return service.get(id);
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
