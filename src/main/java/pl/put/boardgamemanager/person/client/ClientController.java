package pl.put.boardgamemanager.person.client;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @GetMapping("/clients/{id}")
    public ClientDTO get(@PathVariable Long id) {
        Client client = repository.findById(id).orElse(null);
        return mapClientToDTO(client);
    }

    @GetMapping("/clients")
    public List<Client> all() {
        return (List<Client>) repository.findAll();
    }

    @PostMapping("/clients")
    public void create(@RequestBody Client client) {
        repository.save(client);
    }

    @PutMapping("/clients/{id}")
    public ClientDTO update(@RequestBody Client newClient, @PathVariable Long id) {
        return repository.findById(id)
                .map(client -> {
                    client.setName(newClient.getName());
                    client.setSurname(newClient.getSurname());
                    client.setEmail(newClient.getEmail());
                    client.setPhoneNumber(newClient.getPhoneNumber());
                    repository.save(client);
                    return mapClientToDTO(client);
                })
                .orElseGet(() -> {
                    repository.save(newClient);
                    return mapClientToDTO(newClient);
                });
    }

    @DeleteMapping("/clients/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }


    private ClientDTO mapClientToDTO(Client client) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(client, ClientDTO.class);
    }

}
