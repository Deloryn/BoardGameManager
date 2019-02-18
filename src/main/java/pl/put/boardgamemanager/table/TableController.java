package pl.put.boardgamemanager.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TableController {

    @Autowired
    private TableRepository repository;

    @GetMapping("/tables/{id}")
    public TableDTO get(@PathVariable Long id) {
        Table table = repository.findById(id).orElse(null);
        return Table.toDTO(table);
    }

    @GetMapping("/tables")
    public List<TableDTO> all() {
        List<TableDTO> dtos = new ArrayList<>();
        repository.findAll().forEach(table -> {
            dtos.add(Table.toDTO(table));
        });
        return dtos;
    }

    @PostMapping("/tables")
    public void create(@RequestBody TableDTO tableDTO) {
        repository.save(Table.fromDTO(tableDTO));
    }

    @PutMapping("/tables")
    public TableDTO update(@RequestBody TableDTO tableDTO) {
        Table table = Table.fromDTO(tableDTO);
        return repository.findById(table.getId())
                .map(currentTable -> {
                    currentTable.updateParams(table);
                    repository.save(currentTable);
                    return Table.toDTO(currentTable);
                })
                .orElseGet(() -> {
                    repository.save(table);
                    return Table.toDTO(table);
                });
    }

    @DeleteMapping("/tables/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
