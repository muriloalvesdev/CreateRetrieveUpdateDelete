package br.com.crud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonDataTransferObject {

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("birth_date")
    private String birthDate;

    public PersonDataTransferObject(String fullName, String birthDate) {
        this.birthDate = birthDate;
        this.fullName = fullName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "PersonDataTransferObject [fullName=" + fullName + ", birthDate="
                + birthDate + "]";
    }

}
