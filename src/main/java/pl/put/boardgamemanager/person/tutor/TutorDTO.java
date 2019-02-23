package pl.put.boardgamemanager.person.tutor;

import pl.put.boardgamemanager.DTO;

public class TutorDTO extends DTO {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private String phoneNumber;

    public boolean validate() {
        if(name == null) {
            this.setErrorMessage("Name cannot be null");
            return false;
        }
        if(name.trim().isEmpty()) {
            this.setErrorMessage("Name cannot be blank");
            return false;
        }
        if(surname == null) {
            this.setErrorMessage("Surname cannot be null");
            return false;
        }
        if(surname.trim().isEmpty()) {
            this.setErrorMessage("Surname cannot be blank");
            return false;
        }
        if(email == null) {
            this.setErrorMessage("Email cannot be null");
            return false;
        }
        if(email.trim().isEmpty()) {
            this.setErrorMessage("Email cannot be blank");
            return false;
        }
        if(phoneNumber == null) {
            this.setErrorMessage("Phone number cannot be null");
            return false;
        }
        if(phoneNumber.trim().isEmpty()) {
            this.setErrorMessage("Phone number cannot be blank");
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
