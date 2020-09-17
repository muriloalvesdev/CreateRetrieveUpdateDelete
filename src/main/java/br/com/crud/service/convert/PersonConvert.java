package br.com.crud.service.convert;

import java.time.LocalDate;
import java.util.Optional;
import br.com.crud.domain.model.Person;
import br.com.crud.dto.PersonDataTransferObject;
import br.com.crud.service.exception.PersonNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PersonConvert {
  static final String MESSAGE_NOT_FOUND_EXCEPTION = "Person not found!";

  public static Person convert(PersonDataTransferObject dto) {
    return Person.newBuilder().fullName(dto.getFullName()).identifier(dto.getIdentifier())
        .birthDate(LocalDate.parse(dto.getBirthDate())).build();
  }

  public static Person convertOptional(Optional<Person> optionalPerson)
      throws PersonNotFoundException {
    return optionalPerson.orElseThrow(() -> new PersonNotFoundException("Person not found!"));
  }

  public static PersonDataTransferObject convertToPatternDTO(Person person) {
    return PersonDataTransferObject.newBuilder().birthDate(person.getBirthDate().toString())
        .fullName(person.getFullName()).identifier(person.getIdentifier()).build();
  }

}
