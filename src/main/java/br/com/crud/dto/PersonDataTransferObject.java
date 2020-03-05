package br.com.crud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonDataTransferObject {

  @JsonProperty("full_name")
  private String fullName;

  @JsonProperty("birth_date")
  private String birthDate;

  @JsonProperty("identifier")
  private String identifier;

  public PersonDataTransferObject(String fullName, String birthDate, String identifier) {
    this.fullName = fullName;
    this.birthDate = birthDate;
    this.identifier = identifier;
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


  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  @Override
  public String toString() {
    return "PersonDataTransferObject [fullName=" + fullName + ", birthDate=" + birthDate + "]";
  }

}
