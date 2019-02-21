package pl.put.boardgamemanager.rest.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.boardgamemanager.dto.PrivateReservationDTO;
import pl.put.boardgamemanager.dto.TableDTO;
import pl.put.boardgamemanager.logic.table.TableService;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin
public class TableController {

    @Autowired
    private TableService service;

    @GetMapping("/tables/{id}")
    public TableDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/tables")
    public List<TableDTO> all() {
        return service.all();
    }

    @PostMapping("/tables/available-at")
    public List<TableDTO> getAvailableTablesAt(@RequestBody PrivateReservationDTO dto) {
        return service.getAvailableTableDTOsAt(Timestamp.valueOf(dto.getReservationTime()), dto.getDuration());
    }

    @PostMapping("/tables")
    public TableDTO create(@RequestBody TableDTO tableDTO) {
        return service.create(tableDTO);
    }

    @PutMapping("/tables")
    public TableDTO update(@RequestBody TableDTO tableDTO) {
        return service.update(tableDTO);
    }

    @DeleteMapping("/tables/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}