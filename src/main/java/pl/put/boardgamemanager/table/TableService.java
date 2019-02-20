package pl.put.boardgamemanager.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.boardgamemanager.reservation.Reservation;
import pl.put.boardgamemanager.reservation.private_reservation.PrivateReservation;
import pl.put.boardgamemanager.reservation.private_reservation.PrivateReservationRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private PrivateReservationRepository privateReservationRepository;

    private List<Table> getReservedTablesAt(Timestamp reservationTime, Integer duration) {
        return privateReservationRepository
                .findAllByReservationTimeAndDuration(reservationTime, duration)
                .stream()
                .map(Reservation::getTableId)
                .map(tableId -> tableRepository.findById(tableId).orElse(null))
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
