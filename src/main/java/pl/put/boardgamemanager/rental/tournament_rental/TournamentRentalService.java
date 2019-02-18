package pl.put.boardgamemanager.rental.tournament_rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentRentalService {

    @Autowired
    private TournamentRentalRepository repository;

    public TournamentRentalDTO get(Long id) {
        TournamentRental tournamentRental = repository.findById(id).orElse(null);
        return TournamentRental.toDTO(tournamentRental);
    }

    public List<TournamentRentalDTO> all() {
        return repository.findAll().stream()
                .map(TournamentRental::toDTO)
                .collect(Collectors.toList());
    }

    public void create(TournamentRentalDTO tournamentRentalDTO) {
        repository.save(TournamentRental.fromDTO(tournamentRentalDTO));
    }

    public TournamentRentalDTO update(TournamentRentalDTO tournamentRentalDTO) {
        TournamentRental tournamentRental = TournamentRental.fromDTO(tournamentRentalDTO);
        return repository.findById(tournamentRental.getCopyId())
                .map(currentTournamentRental -> {
                    currentTournamentRental.updateParams(tournamentRental);
                    repository.save(currentTournamentRental);
                    return TournamentRental.toDTO(currentTournamentRental);
                })
                .orElseGet(() -> {
                    create(tournamentRentalDTO);
                    return tournamentRentalDTO;
                });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
