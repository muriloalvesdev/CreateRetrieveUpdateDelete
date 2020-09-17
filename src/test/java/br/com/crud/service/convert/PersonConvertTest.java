package br.com.crud.service.convert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import br.com.crud.domain.model.Person;
import br.com.crud.dto.PersonDataTransferObject;
import br.com.crud.providers.PersonConstants;
import br.com.crud.providers.PersonDTOProviderTests;
import br.com.crud.providers.PersonEntityProviderTests;
import br.com.crud.service.exception.PersonNotFoundException;

class PersonConvertTest implements PersonConstants {

  @ParameterizedTest
  @ArgumentsSource(PersonDTOProviderTests.class)
  void shouldConvertDataTransferObjectToEntity(PersonDataTransferObject personDTO) {
    Person person = PersonConvert.convert(personDTO);

    asserts(person.getFullName(), person.getBirthDate().toString(), person.getIdentifier());
  }

  @Test
  void shouldReturnPersonNotFoundException() throws PersonNotFoundException {
    Exception exception = assertThrows(PersonNotFoundException.class, () -> {
      PersonConvert.convertOptional(Optional.empty());
    });
    assertTrue(exception instanceof PersonNotFoundException);
    assertEquals(PersonConvert.MESSAGE_NOT_FOUND_EXCEPTION, exception.getMessage());
  }

  @ParameterizedTest
  @ArgumentsSource(PersonEntityProviderTests.class)
  void shouldConvertOptionalPersonToPersonEntity(Person person) throws PersonNotFoundException {
    Optional<Person> optionalPerson = Optional.of(person);
    Person personConverted = PersonConvert.convertOptional(optionalPerson);

    asserts(personConverted.getFullName(), personConverted.getBirthDate().toString(),
        personConverted.getIdentifier());
  }

  @ParameterizedTest
  @ArgumentsSource(PersonEntityProviderTests.class)
  void shouldConvertPersonEntityToPersonDataTransferObject(Person person) {
    PersonDataTransferObject personDataTransferObject = PersonConvert.convertToPatternDTO(person);

    asserts(personDataTransferObject.getFullName(),
        personDataTransferObject.getBirthDate().toString(),
        personDataTransferObject.getIdentifier());
  }

  private void asserts(String fullName, String birthDate, String identifier) {
    assertEquals(FULL_NAME, fullName);
    assertEquals(BIRTH_DATE, birthDate);
    assertEquals(IDENTIFIER, identifier);
  }
}
