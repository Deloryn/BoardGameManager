package pl.put.boardgamemanager.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository repository;

    public TournamentDTO get(Long id) {
        Tournament tournament = repository.findById(id).orElse(null);
        return Tournament.toDTO(tournament);
    }

    public List<TournamentDTO> all() {
        return repository.findAll().stream()
                .map(Tournament::toDTO)
                .collect(Collectors.toList());
    }

    public void create(TournamentDTO tournamentDTO) {
        repository.save(Tournament.fromDTO(tournamentDTO));
    }

    public TournamentDTO update(TournamentDTO tournamentDTO) {
        Tournament tournament = Tournament.fromDTO(tournamentDTO);
        return repository.findById(tournament.getId())
                .map(currentTournament -> {
                    currentTournament.updateParams(tournament);
                    repository.save(currentTournament);
                    return Tournament.toDTO(currentTournament);
                })
                .orElseGet(() -> {
                    create(tournamentDTO);
                    return tournamentDTO;
                });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
