package pl.put.boardgamemanager.person.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.game.Game;
import pl.put.boardgamemanager.game.GameRepository;
import pl.put.boardgamemanager.reservation.private_reservation.PrivateReservation;
import pl.put.boardgamemanager.reservation.private_reservation.PrivateReservationRepository;
import pl.put.boardgamemanager.table.Table;
import pl.put.boardgamemanager.table.TableRepository;
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

    @Autowired
    private PrivateReservationRepository reservationRepository;

    @Autowired
    private TableRepository tableRepository;

    private List<Tournament> getNotParticipatedTournaments(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) return tournamentRepository.findAll();
        else {
            List<Tournament> participatedTournaments = client.getTournaments();
            List<Tournament> allTournaments = tournamentRepository.findAll();

            allTournaments.removeAll(participatedTournaments);

            return allTournaments;
        }
    }

    private ClientTournamentDTO tournamentToClientTournamentDTO(Tournament tournament) {
        Game game = gameRepository.findById(tournament.getGameId()).orElse(null);
        if (game == null) return null;
        else {
            ClientTournamentDTO dto = new ClientTournamentDTO();
            dto.setTournamentDTO(tournament.toDTO());
            dto.setGameName(game.getName());
            return dto;
        }
    }

    private ClientReservationDTO reservationToClientReservationDTO(PrivateReservation reservation) {
        Table table = tableRepository.findById(reservation.getTableId()).orElse(null);
        if (table == null) return null;
        else {
            ClientReservationDTO dto = new ClientReservationDTO();
            dto.setPrivateReservation(reservation);
            dto.setNumberOfSits(table.getNumberOfSits());
            return dto;
        }
    }

    public ClientDTO getClientDTOById(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) return null;
        else return client.toDTO();
    }

    public ClientDTO getClientDTOByEmail(String email) {
        Client client = clientRepository.findByEmail(email);
        if (client == null) return null;
        else return client.toDTO();
    }

    public Boolean exists(Long id) {
        return clientRepository.existsById(id);
    }

    public List<ClientTournamentDTO> getParticipatedTournamentDTOs(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) return null;
        else return client.getTournaments().stream()
                .map(this::tournamentToClientTournamentDTO)
                .collect(Collectors.toList());
    }

    public List<ClientTournamentDTO> getAvailableTournamentDTOs(Long id) {
        return getNotParticipatedTournaments(id).stream()
                .filter(x -> x.getParticipants().size() < x.getMaxPlayers())
                .map(this::tournamentToClientTournamentDTO)
                .collect(Collectors.toList());
    }

    public List<ClientReservationDTO> getClientReservationDTOs(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) return null;
        else {
            return reservationRepository.findAllByClientId(id).stream()
                    .map(this::reservationToClientReservationDTO)
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
