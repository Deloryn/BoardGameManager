package pl.put.boardgamemanager.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableService {

    @Autowired
    private TableRepository repository;

    public TableDTO get(Long id) {
        Table table = repository.findById(id).orElse(null);
        return Table.toDTO(table);
    }

    public List<TableDTO> all() {
        return repository.findAll().stream()
                .map(Table::toDTO)
                .collect(Collectors.toList());
    }

    public void create(TableDTO tableDTO) {
        repository.save(Table.fromDTO(tableDTO));
    }

    public TableDTO update(TableDTO tableDTO) {
        Table table = Table.fromDTO(tableDTO);
        return repository.findById(table.getId())
                .map(currentTable -> {
                    currentTable.updateParams(table);
                    repository.save(currentTable);
                    return Table.toDTO(currentTable);
                })
                .orElseGet(() -> {
                    create(tableDTO);
                    return tableDTO;
                });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
