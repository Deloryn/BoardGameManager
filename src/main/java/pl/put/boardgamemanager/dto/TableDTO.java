package pl.put.boardgamemanager.dto;

public class TableDTO extends DTO {

    private Long id;

    private Short numberOfSits;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Short getNumberOfSits() {
        return numberOfSits;
    }

    public void setNumberOfSits(Short numberOfSits) {
        this.numberOfSits = numberOfSits;
    }

}
