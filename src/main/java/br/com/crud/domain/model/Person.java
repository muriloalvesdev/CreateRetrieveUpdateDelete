package br.com.crud.domain.model;

import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "person", uniqueConstraints = {@UniqueConstraint(columnNames = {"identifier"})})
public class Person extends BaseEntity {

  private static final long serialVersionUID = 3642835563572945200L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID uuid;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "identifier")
  private String identifier;

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @SuppressWarnings("unused")
  private Person() {}

  public Person(String fullName, String identifier, LocalDate birthDate) {
    this.fullName = fullName;
    this.identifier = identifier;
    this.birthDate = birthDate;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getFullName() {
    return fullName;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public UUID getUuid() {
    return uuid;
  }

}
