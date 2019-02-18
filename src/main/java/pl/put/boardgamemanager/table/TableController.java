package pl.put.boardgamemanager.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/tables")
    public void create(@RequestBody TableDTO tableDTO) {
        service.create(tableDTO);
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
