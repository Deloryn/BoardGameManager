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
        if(table == null) return null;
        else return table.toDTO();
    }

    public List<TableDTO> all() {
        return repository.findAll().stream()
                .map(Table::toDTO)
                .collect(Collectors.toList());
    }

    public TableDTO create(TableDTO dto) {
        Table table = new Table();
        table.updateParamsFrom(dto);
        repository.save(table);
        return table.toDTO();
    }

    public TableDTO update(TableDTO dto) {
        return repository.findById(dto.getId())
                .map(existingTable -> {
                    existingTable.updateParamsFrom(dto);
                    repository.save(existingTable);
                    return existingTable.toDTO();
                })
                .orElseGet(() -> create(dto));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
