package br.com.crud.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.crud.domain.model.Person;
import br.com.crud.domain.repository.PersonRepository;
import br.com.crud.dto.PersonDataTransferObject;
import br.com.crud.service.convert.PersonConvert;
import br.com.crud.service.exception.PersonConflictException;
import br.com.crud.service.exception.PersonNotFoundException;

@Service
public class PersonServiceImpl implements PersonService {

  private static final Logger LOG = Logger.getLogger(PersonServiceImpl.class);

  @Autowired
  private PersonRepository personRepository;

  @Override
  public PersonDataTransferObject save(PersonDataTransferObject personDTO) {
    try {
      Person person = PersonConvert.convert(personDTO);
      personRepository.saveAndFlush(person);
    } catch (PersonConflictException e) {
      LOG.error("Error persist personDTO, error: " + e.getMessage());
    }
    return personDTO;
  }

  @Override
  public PersonDataTransferObject find(String identifier) {
    PersonDataTransferObject personDataTransferObject = null;
    try {
      Optional<Person> optionalPerson = getOptionalPersonByIdentifier(identifier);

      Person person = PersonConvert.convertOptional(optionalPerson);
      personDataTransferObject = PersonConvert.convertToPatternDTO(person);
    } catch (PersonNotFoundException e) {
      LOG.error("IDENTIFIER[" + identifier + "] informed not found, error: " + e.getMessage(), e);
    }
    return personDataTransferObject;
  }

  @Override
  public void delete(String identifier) {
    try {
      Optional<Person> optionalPerson = getOptionalPersonByIdentifier(identifier);

      Person person = PersonConvert.convertOptional(optionalPerson);

      personRepository.delete(person);
    } catch (Exception e) {
      LOG.error("FULL-NAME [" + identifier + "] informed not found, error: " + e.getMessage(), e);
    }
  }

  @Override
  public void update(String identifier, PersonDataTransferObject personDTO) {
    try {
      Optional<Person> optionalPerson = getOptionalPersonByIdentifier(identifier);
      Person person = PersonConvert.convertOptional(optionalPerson);

      person.setBirthDate(LocalDate.parse(personDTO.getBirthDate()));
      person.setFullName(personDTO.getFullName());
      person.setIdentifier(personDTO.getIdentifier());

      personRepository.save(person);
    } catch (PersonNotFoundException e) {
      LOG.error("UUID [" + identifier + "] informed not found, error: " + e.getMessage(), e);
    }

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
