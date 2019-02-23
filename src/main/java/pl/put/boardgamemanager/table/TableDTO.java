package pl.put.boardgamemanager.table;

import pl.put.boardgamemanager.DTO;

public class TableDTO extends DTO {

    private Long id;

    private Short numberOfSits;

    public boolean validate() {
        if(numberOfSits == null) {
            this.setErrorMessage("Number of sits cannot be null");
            return false;
        }

        if(numberOfSits <= 0) {
            this.setErrorMessage("Number of sits must be greater than 0");
            return false;
        }
        return true;
    }

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
