package pl.put.boardgamemanager.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.boardgamemanager.ListDTO;
import pl.put.boardgamemanager.TimeDTO;

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
    public ListDTO<TableDTO> all() {
        return service.all();
    }

    @PostMapping("/tables/available-at")
    public ListDTO<TableDTO> getAvailableTablesAt(@RequestBody TimeDTO dto) {
        if(!dto.validate()) {
            ListDTO<TableDTO> resultDTO = new ListDTO<>();
            resultDTO.setErrorMessage(dto.getErrorMessage());
            return resultDTO;
        }
        else return service.getAvailableTableDTOsAt(dto.getStartTime(), dto.getDuration());
    }

    @PostMapping("/tables")
    public TableDTO create(@RequestBody TableDTO tableDTO) {
        if(!tableDTO.validate()) return tableDTO;
        else return service.create(tableDTO);
    }

    @PutMapping("/tables")
    public TableDTO update(@RequestBody TableDTO tableDTO) {
        if(tableDTO.getId() == null) {
            tableDTO.setErrorMessage("Id in updating cannot be null");
            return tableDTO;
        }
        if(!tableDTO.validate()) return tableDTO;
        else return service.update(tableDTO);
    }

    @DeleteMapping("/tables/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
