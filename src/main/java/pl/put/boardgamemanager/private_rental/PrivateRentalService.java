package pl.put.boardgamemanager.private_rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.Utils;
import pl.put.boardgamemanager.game.GameRepository;
import pl.put.boardgamemanager.game_copy.GameCopyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivateRentalService {

    @Autowired
    private PrivateRentalRepository privateRentalRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameCopyRepository gameCopyRepository;

    public PrivateRentalDTO get(Long id) {
        PrivateRental rental = privateRentalRepository.findById(id).orElse(null);
        if(rental == null) return null;
        else return Utils.assignGameNameTo(rental.toDTO(), gameRepository, gameCopyRepository);
    }

    public List<PrivateRentalDTO> all() {
        return privateRentalRepository.findAll().stream()
                .map(PrivateRental::toDTO)
                .map(dto -> Utils.assignGameNameTo(dto, gameRepository, gameCopyRepository))
                .collect(Collectors.toList());
    }

    public PrivateRentalDTO create(PrivateRentalDTO dto) {
        PrivateRental rental = new PrivateRental();
        rental.updateParamsFrom(dto);
        privateRentalRepository.save(rental);
        return Utils.assignGameNameTo(rental.toDTO(), gameRepository, gameCopyRepository);
    }

    public PrivateRentalDTO update(PrivateRentalDTO dto) {
        return privateRentalRepository.findById(dto.getId())
                .map(existingRental -> {
                    existingRental.updateParamsFrom(dto);
                    privateRentalRepository.save(existingRental);
                    return Utils.assignGameNameTo(existingRental.toDTO(), gameRepository, gameCopyRepository);
                })
                .orElseGet(() -> create(dto));
    }

    public void delete(Long id) {
        privateRentalRepository.deleteById(id);
    }

}
