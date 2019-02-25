package pl.put.boardgamemanager.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.ListDTO;
import pl.put.boardgamemanager.private_reservation.PrivateReservation;
import pl.put.boardgamemanager.private_reservation.PrivateReservationRepository;
import pl.put.boardgamemanager.tournament.Tournament;
import pl.put.boardgamemanager.tournament.TournamentRepository;
import pl.put.boardgamemanager.tournament_reservation.TournamentReservation;
import pl.put.boardgamemanager.tournament_reservation.TournamentReservationRepository;
import pl.put.boardgamemanager.Utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private PrivateReservationRepository privateReservationRepository;

    @Autowired
    private TournamentReservationRepository tournamentReservationRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    public TableDTO get(Long id) {
        Table table = tableRepository.findById(id).orElse(null);
        if (table == null) {
            TableDTO dto = new TableDTO();
            dto.setErrorMessage("There is no table with the given id");
            return dto;
        }
        else return table.toDTO();
    }

    public ListDTO<TableDTO> all() {
        ListDTO<TableDTO> resultDTO = new ListDTO<>();
        resultDTO.setValues(tableRepository.findAll().stream()
                .map(Table::toDTO)
                .collect(Collectors.toList()));
        return resultDTO;
    }

    public TableDTO create(TableDTO dto) {
        Table table = new Table();
        table.updateParamsFrom(dto);
        try {
            tableRepository.save(table);
            return table.toDTO();
        }
        catch(DataIntegrityViolationException ex) {
            dto.setErrorMessage("Given data violates data constraints");
            return dto;
        }
    }

    public TableDTO update(TableDTO dto) {
        return tableRepository.findById(dto.getId())
                .map(existingTable -> {
                    existingTable.updateParamsFrom(dto);
                    try {
                        tableRepository.save(existingTable);
                        return existingTable.toDTO();
                    }
                    catch(DataIntegrityViolationException ex) {
                        dto.setErrorMessage("Given data violates data constraints");
                        return dto;
                    }
                })
                .orElseGet(() -> create(dto));
    }

    public void delete(Long id) {
        tableRepository.deleteById(id);
    }

    public ListDTO<TableDTO> getAvailableTableDTOsAtPrivate(LocalDateTime reservationTime, Integer duration, Long targetId) {
        ListDTO<TableDTO> resultDTO = new ListDTO<>();

        List<Table> allTables = tableRepository.findAll();
        allTables.removeAll(Stream
                .concat(getReservedPrivateTablesAt(reservationTime, duration, targetId).stream(),
                        getReservedTournamentTablesAt(reservationTime, duration, null).stream())
                .collect(Collectors.toList()));

        resultDTO.setValues(allTables.stream()
                .map(Table::toDTO)
                .collect(Collectors.toList()));

        return resultDTO;
    }

    public ListDTO<TableDTO> getAvailableTableDTOsAtTournament(LocalDateTime reservationTime, Integer duration, Long targetId) {
        ListDTO<TableDTO> resultDTO = new ListDTO<>();

        List<Table> allTables = tableRepository.findAll();
        allTables.removeAll(Stream
                .concat(getReservedPrivateTablesAt(reservationTime, duration, null).stream(),
                        getReservedTournamentTablesAt(reservationTime, duration, targetId).stream())
                .collect(Collectors.toList()));

        resultDTO.setValues(allTables.stream()
                .map(Table::toDTO)
                .collect(Collectors.toList()));

        return resultDTO;
    }

    private List<Table> getReservedPrivateTablesAt(LocalDateTime reservationTime, Integer duration, Long targetId) {
        PrivateReservation desiredReservation = new PrivateReservation();
        desiredReservation.setStartTime(reservationTime);
        desiredReservation.setDuration(duration);

        return privateReservationRepository
                .findAll()
                .stream()
                .filter(reservation -> !reservation.getId().equals(targetId))
                .filter(reservation -> Utils.isEventDuringAnother(reservation, desiredReservation))
                .map(reservation -> tableRepository.findById(reservation.getTableId()).orElse(null))
                .collect(Collectors.toList());
    }

    private List<Table> getReservedTournamentTablesAt(LocalDateTime reservationTime, Integer duration, Long targetId) {
        PrivateReservation desiredReservation = new PrivateReservation();
        desiredReservation.setStartTime(reservationTime);
        desiredReservation.setDuration(duration);
        return tournamentReservationRepository
                .findAll()
                .stream()
                .map(TournamentReservation::getTournamentId)
                .filter(tournamentId -> !tournamentId.equals(targetId))
                .map(tournamentId -> tournamentRepository.findById(tournamentId).orElse(null))
                .collect(Collectors.toList())
                .stream()
                .filter(tournament -> Utils.isEventDuringAnother(tournament, desiredReservation))
                .map(Tournament::getId)
                .map(tournamentId -> tournamentReservationRepository.findAllByTournamentId(tournamentId))
                .flatMap(List::stream)
                .map(reservation -> tableRepository.findById(reservation.getTableId()).orElse(null))
                .collect(Collectors.toList());
    }
}
