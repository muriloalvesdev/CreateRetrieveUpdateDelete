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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Getter
@Setter
@Builder(builderMethodName = "newBuilder")
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

}
