package pl.put.boardgamemanager.person.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.game.Game;
import pl.put.boardgamemanager.game.GameRepository;
import pl.put.boardgamemanager.tournament.Tournament;
import pl.put.boardgamemanager.tournament.TournamentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private GameRepository gameRepository;

    private ClientTournamentsDTO tournamentToClientTournamentDTO(Tournament tournament) {
        Game game = gameRepository.findById(tournament.getGameId()).orElse(null);
        if (game == null) return null;
        else {
            ClientTournamentsDTO dto = new ClientTournamentsDTO();
            dto.setTournamentDTO(tournament.toDTO());
            dto.setGameName(game.getName());
            return dto;
        }
    }

    public ClientDTO getById(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) return null;
        else return client.toDTO();
    }

    public ClientDTO getByEmail(String email) {
        Client client = clientRepository.findByEmail(email);
        if(client == null) return null;
        else return client.toDTO();
    }

    public Boolean exists(Long id) {
        return clientRepository.existsById(id);
    }

    public List<ClientTournamentsDTO> getParticipatedTournamentDTOs(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) return null;
        else return client.getTournaments().stream()
                .map(this::tournamentToClientTournamentDTO)
                .collect(Collectors.toList());
    }

    public List<ClientTournamentsDTO> getAvailableTournamentDTOs(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) return null;
        else {
            List<Tournament> participatedTournaments = client.getTournaments();
            List<Tournament> allTournaments = tournamentRepository.findAll();

            allTournaments.removeAll(participatedTournaments);

            return allTournaments.stream()
                    .filter(x -> x.getParticipants().size() < x.getMaxPlayers())
                    .map(this::tournamentToClientTournamentDTO)
                    .collect(Collectors.toList());
        }
    }

    public List<ClientDTO> all() {
        return clientRepository.findAll().stream()
                .map(Client::toDTO)
                .collect(Collectors.toList());
    }

    public ClientDTO create(ClientDTO dto) {
        Client client = new Client();
        client.updateParamsFrom(dto);
        clientRepository.save(client);
        return client.toDTO();
    }

    public ClientDTO update(ClientDTO dto) {
        return clientRepository.findById(dto.getId())
                .map(existingClient -> {
                    existingClient.updateParamsFrom(dto);
                    clientRepository.save(existingClient);
                    return existingClient.toDTO();
                })
                .orElseGet(() -> create(dto));
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
