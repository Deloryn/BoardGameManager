package pl.put.boardgamemanager.person.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @GetMapping("/clients/{id}")
    public ClientDTO get(@PathVariable Long id) {
        Client client = repository.findById(id).orElse(null);
        return Client.toDTO(client);
    }

    @GetMapping("/clients")
    public List<ClientDTO> all() {
        List<ClientDTO> dtos = new ArrayList<>();
        repository.findAll().forEach(client -> {
            dtos.add(Client.toDTO(client));
        });
        return dtos;
    }

    @PostMapping("/clients")
    public void create(@RequestBody Client client) {
        repository.save(client);
    }

    @PutMapping("/clients/{id}")
    public ClientDTO update(@RequestBody Client newClient, @PathVariable Long id) {
        return repository.findById(id)
                .map(client -> {
                    client.updateParams(newClient);
                    repository.save(client);
                    return Client.toDTO(client);
                })
                .orElseGet(() -> {
                    repository.save(newClient);
                    return Client.toDTO(newClient);
                });
    }

    @DeleteMapping("/clients/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
