package pl.put.boardgamemanager.model;

import pl.put.boardgamemanager.dto.DTO;

public interface Model {
    void updateParamsFrom(DTO dto);
    DTO toDTO();
}
