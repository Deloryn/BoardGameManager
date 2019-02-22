package pl.put.boardgamemanager;

import pl.put.boardgamemanager.game.Game;
import pl.put.boardgamemanager.game.GameRepository;
import pl.put.boardgamemanager.game_copy.GameCopy;
import pl.put.boardgamemanager.game_copy.GameCopyRepository;
import pl.put.boardgamemanager.private_rental.PrivateRentalDTO;
import pl.put.boardgamemanager.tournament_rental.TournamentRentalDTO;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class Utils {

    public static PrivateRentalDTO assignGameNameTo(PrivateRentalDTO dto, GameRepository gameRepository, GameCopyRepository gameCopyRepository) {
        if(dto == null) return null;
        GameCopy copy = gameCopyRepository.findById(dto.getCopyId()).orElse(null);
        if(copy == null) return null;
        Game game = gameRepository.findById(copy.getGameId()).orElse(null);
        if(game == null) return null;

        dto.setReadOnlyGameName(game.getName());

        return dto;
    }

    public static TournamentRentalDTO assignGameNameTo(TournamentRentalDTO dto, GameRepository gameRepository, GameCopyRepository gameCopyRepository) {
        if(dto == null) return null;

        GameCopy copy = gameCopyRepository.findById(dto.getCopyId()).orElse(null);
        if(copy == null) return null;
        Game game = gameRepository.findById(copy.getGameId()).orElse(null);
        if(game == null) return null;

        dto.setReadOnlyGameName(game.getName());

        return dto;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static boolean isEventDuringAnother(TimeEvent event, TimeEvent another) {
        if (event.getStartTime().isBefore(another.getStartTime()))
            return calculateFinishTime(event).isAfter(another.getStartTime());
        else
            return event.getStartTime().isBefore(calculateFinishTime(another));
    }

    private static LocalDateTime calculateFinishTime(TimeEvent event) {
        return event.getStartTime().plusMinutes(event.getDuration());
    }

}
