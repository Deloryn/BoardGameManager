package pl.put.boardgamemanager.logic.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.dto.TableDTO;
import pl.put.boardgamemanager.model.reservation.private_reservation.PrivateReservation;
import pl.put.boardgamemanager.logic.reservation.private_reservation.PrivateReservationRepository;
import pl.put.boardgamemanager.logic.reservation.tournament_reservation.TournamentReservationRepository;
import pl.put.boardgamemanager.model.table.Table;
import pl.put.boardgamemanager.util.Utils;

import java.sql.Timestamp;
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


    private List<Table> getReservedPrivateTablesAt(Timestamp reservationTime, Integer duration) {
        PrivateReservation desiredReservation = new PrivateReservation();
        desiredReservation.setStartTime(reservationTime);
        desiredReservation.setDuration(duration);
        return privateReservationRepository
                .findAll()
                .stream()
                .filter(reservation -> Utils.isEventDuringAnother(reservation, desiredReservation))
                .map(reservation -> tableRepository.findById(reservation.getTableId()).orElse(null))
                .collect(Collectors.toList());
    }

    private List<Table> getReservedTournamentTablesAt() {
        return tournamentReservationRepository
                .findAll()
                .stream()
                .map(reservation -> tableRepository.findById(reservation.getTableId()).orElse(null))
                .collect(Collectors.toList());
    }

    private List<Table> getReservedTablesAt(Timestamp reservationTime, Integer duration) {
        return Stream
                .concat(getReservedPrivateTablesAt(reservationTime, duration).stream(),
                        getReservedTournamentTablesAt().stream())
                .collect(Collectors.toList());
    }

    public TableDTO get(Long id) {
        Table table = tableRepository.findById(id).orElse(null);
        if (table == null) return null;
        else return table.toDTO();
    }

    public List<TableDTO> getAvailableTableDTOsAt(Timestamp reservationTime, Integer duration) {
        List<Table> allTables = tableRepository.findAll();
        allTables.removeAll(getReservedTablesAt(reservationTime, duration));
        return allTables.stream()
                .map(Table::toDTO)
                .collect(Collectors.toList());
    }

    public List<TableDTO> all() {
        return tableRepository.findAll().stream()
                .map(Table::toDTO)
                .collect(Collectors.toList());
    }

    public TableDTO create(TableDTO dto) {
        Table table = new Table();
        table.updateParamsFrom(dto);
        tableRepository.save(table);
        return table.toDTO();
    }

    public TableDTO update(TableDTO dto) {
        return tableRepository.findById(dto.getId())
                .map(existingTable -> {
                    existingTable.updateParamsFrom(dto);
                    tableRepository.save(existingTable);
                    return existingTable.toDTO();
                })
                .orElseGet(() -> create(dto));
    }

    public void delete(Long id) {
        tableRepository.deleteById(id);
    }

}
