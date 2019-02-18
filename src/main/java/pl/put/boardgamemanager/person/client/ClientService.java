package pl.put.boardgamemanager.person.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public ClientDTO get(Long id) {
        Client client = repository.findById(id).orElse(null);
        return Client.toDTO(client);
    }

    public List<ClientDTO> all() {
        return repository.findAll().stream()
                .map(Client::toDTO)
                .collect(Collectors.toList());
    }

    public void create(ClientDTO clientDTO) {
        repository.save(Client.fromDTO(clientDTO));
    }

    public ClientDTO update(ClientDTO clientDTO) {
        Client client = Client.fromDTO(clientDTO);
        return repository.findById(client.getId())
                .map(currentClient -> {
                    currentClient.updateParams(client);
                    repository.save(currentClient);
                    return Client.toDTO(currentClient);
                })
                .orElseGet(() -> {
                    create(clientDTO);
                    return clientDTO;
                });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
