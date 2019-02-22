package pl.put.boardgamemanager.tournament_rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.Utils;
import pl.put.boardgamemanager.game.GameRepository;
import pl.put.boardgamemanager.game_copy.GameCopyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentRentalService {

    @Autowired
    private TournamentRentalRepository tournamentRentalRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameCopyRepository gameCopyRepository;

    public TournamentRentalDTO get(Long id) {
        TournamentRental rental = tournamentRentalRepository.findById(id).orElse(null);
        if(rental == null) return null;
        else return Utils.assignGameNameTo(rental.toDTO(), gameRepository, gameCopyRepository);
    }

    public List<TournamentRentalDTO> all() {
        return tournamentRentalRepository.findAll().stream()
                .map(TournamentRental::toDTO)
                .map(dto -> Utils.assignGameNameTo(dto, gameRepository, gameCopyRepository))
                .collect(Collectors.toList());
    }

    public TournamentRentalDTO create(TournamentRentalDTO dto) {
        TournamentRental rental = new TournamentRental();
        rental.updateParamsFrom(dto);
        tournamentRentalRepository.save(rental);
        return Utils.assignGameNameTo(rental.toDTO(), gameRepository, gameCopyRepository);
    }

    public TournamentRentalDTO update(TournamentRentalDTO dto) {
        return tournamentRentalRepository.findById(dto.getId())
                .map(existingRental -> {
                    existingRental.updateParamsFrom(dto);
                    tournamentRentalRepository.save(existingRental);
                    return Utils.assignGameNameTo(existingRental.toDTO(), gameRepository, gameCopyRepository);
                })
                .orElseGet(() -> create(dto));
    }

    public void delete(Long id) {
        tournamentRentalRepository.deleteById(id);
    }

}
