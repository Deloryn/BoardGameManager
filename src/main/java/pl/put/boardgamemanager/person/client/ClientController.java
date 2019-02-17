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
    public void create(@RequestBody ClientDTO clientDTO) {
        repository.save(Client.fromDTO(clientDTO));
    }

    @PutMapping("/clients")
    public ClientDTO update(@RequestBody ClientDTO clientDTO) {
        Client client = Client.fromDTO(clientDTO);
        return repository.findById(client.getId())
                .map(currentClient -> {
                    currentClient.updateParams(client);
                    repository.save(currentClient);
                    return Client.toDTO(currentClient);
                })
                .orElseGet(() -> {
                    repository.save(client);
                    return Client.toDTO(client);
                });
    }

    @DeleteMapping("/clients/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
