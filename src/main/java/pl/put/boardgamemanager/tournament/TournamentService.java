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
        if(tournament == null) return null;
        else return tournament.toDTO();
    }

    public List<TournamentDTO> all() {
        return repository.findAll().stream()
                .map(Tournament::toDTO)
                .collect(Collectors.toList());
    }

    public TournamentDTO create(TournamentDTO dto) {
        Tournament tournament = new Tournament();
        tournament.updateParamsFrom(dto);
        repository.save(tournament);
        return tournament.toDTO();
    }

    public TournamentDTO update(TournamentDTO dto) {
        return repository.findById(dto.getId())
                .map(existingTournament -> {
                    existingTournament.updateParamsFrom(dto);
                    repository.save(existingTournament);
                    return existingTournament.toDTO();
                })
                .orElseGet(() -> create(dto));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
