package pl.put.boardgamemanager.person.client;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    private ClientDTO mapClientToDTO(Client client) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(client, ClientDTO.class);
    }
}
