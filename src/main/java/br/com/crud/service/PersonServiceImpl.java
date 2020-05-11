package br.com.crud.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import br.com.crud.domain.model.Person;
import br.com.crud.domain.repository.PersonRepository;
import br.com.crud.dto.PersonDataTransferObject;
import br.com.crud.service.convert.PersonConvert;

@Service
public class PersonServiceImpl implements PersonService {

  private PersonRepository personRepository;

  public PersonServiceImpl(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  public PersonDataTransferObject save(PersonDataTransferObject personDTO) {
    Person person = PersonConvert.convert(personDTO);
    personRepository.saveAndFlush(person);
    return personDTO;
  }

  @Override
  public PersonDataTransferObject find(String identifier) {
    PersonDataTransferObject personDataTransferObject = null;
    Optional<Person> optionalPerson = getOptionalPersonByIdentifier(identifier);

    Person person = PersonConvert.convertOptional(optionalPerson);
    personDataTransferObject = PersonConvert.convertToPatternDTO(person);
    return personDataTransferObject;
  }

  @Override
  public void delete(String identifier) {
    Optional<Person> optionalPerson = getOptionalPersonByIdentifier(identifier);

    Person person = PersonConvert.convertOptional(optionalPerson);

    personRepository.delete(person);
  }

  @Override
  public void update(String identifier, PersonDataTransferObject personDTO) {
    Optional<Person> optionalPerson = getOptionalPersonByIdentifier(identifier);
    Person person = PersonConvert.convertOptional(optionalPerson);

    person.setBirthDate(LocalDate.parse(personDTO.getBirthDate()));
    person.setFullName(personDTO.getFullName());
    person.setIdentifier(personDTO.getIdentifier());

    personRepository.save(person);
  }

  @Override
  public List<PersonDataTransferObject> findAll() {
    List<PersonDataTransferObject> personsDTO = new ArrayList<>();

    personRepository.findAll().stream().forEach(person -> {
      PersonDataTransferObject personDataTransferObject = new PersonDataTransferObject(
          person.getFullName(), person.getBirthDate().toString(), person.getIdentifier());

      personsDTO.add(personDataTransferObject);
    });

    return personsDTO;
  }

  private Optional<Person> getOptionalPersonByIdentifier(String identifier) {
    return personRepository.findByIdentifier(identifier);
  }

}
