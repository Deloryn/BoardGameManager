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
        if(client == null) return null;
        else return client.toDTO();
    }

    public List<ClientDTO> all() {
        return repository.findAll().stream()
                .map(Client::toDTO)
                .collect(Collectors.toList());
    }

    public ClientDTO create(ClientDTO dto) {
        Client client = new Client();
        client.updateParamsFrom(dto);
        repository.save(client);
        return client.toDTO();
    }

    public ClientDTO update(ClientDTO dto) {
        return repository.findById(dto.getId())
                .map(existingClient -> {
                    existingClient.updateParamsFrom(dto);
                    repository.save(existingClient);
                    return existingClient.toDTO();
                })
                .orElseGet(() -> create(dto));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
