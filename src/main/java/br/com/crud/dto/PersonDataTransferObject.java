package br.com.crud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonDataTransferObject {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("birth_date")
    private String birthDate;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public PersonDataTransferObject(String firstName, String lastName,
            String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "PersonDataTransferObject [firstName=" + firstName
                + ", lastName=" + lastName + ", birthDate=" + birthDate + "]";
    }

}
