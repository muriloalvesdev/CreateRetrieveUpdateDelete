package br.com.crud.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import br.com.crud.domain.model.Person;
import br.com.crud.domain.repository.PersonRepository;
import br.com.crud.dto.PersonDataTransferObject;
import br.com.crud.providers.PersonConstants;
import br.com.crud.providers.PersonEntityProviderTests;

class PersonServiceTest implements PersonConstants {

  private PersonService service;
  private PersonRepository repository;
  private PersonDataTransferObject dto;

  @BeforeEach
  void beforeCreatePageRequest() {
    this.repository = Mockito.spy(PersonRepository.class);
    this.service = new PersonServiceImpl(this.repository);
    this.dto =
        PersonDataTransferObject.newBuilder().birthDate(LocalDate.parse(BIRTH_DATE).toString())
            .identifier(IDENTIFIER).fullName(FULL_NAME).build();
  }

  @ParameterizedTest
  @ArgumentsSource(PersonEntityProviderTests.class)
  void shouldSavePerson(Person person) {

    BDDMockito.given(this.repository.saveAndFlush(person)).willReturn(person);

    this.service.save(this.dto);

    BDDMockito.verify(this.repository, times(1)).saveAndFlush(any(Person.class));
  }

  @ParameterizedTest
  @ArgumentsSource(PersonEntityProviderTests.class)
  void shouldFindPersonByIdentifier(Person person) {
    BDDMockito.given(this.repository.findByIdentifier(IDENTIFIER)).willReturn(Optional.of(person));

    this.service.find(IDENTIFIER);

    BDDMockito.verify(this.repository, times(1)).findByIdentifier(IDENTIFIER);
  }

}
