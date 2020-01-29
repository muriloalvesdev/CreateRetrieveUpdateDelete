package br.com.crud.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crud.domain.model.Person;
import br.com.crud.domain.repository.PersonRepository;
import br.com.crud.dto.PersonDataTransferObject;
import br.com.crud.service.convert.PersonConvert;

@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger LOG = Logger.getLogger(PersonServiceImpl.class);

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person save(PersonDataTransferObject personDTO) {

        Person person = PersonConvert.convert(personDTO);

        return personRepository.save(person);
    }

    @Override
    public PersonDataTransferObject find(String uuid) {
        PersonDataTransferObject personDataTransferObject = null;
        try {
            Optional<Person> optionalPerson = getOptionalPerson(uuid);

            Person person = PersonConvert.convertOptional(optionalPerson);
            personDataTransferObject = PersonConvert
                    .convertToPatternDTO(person);
        } catch (Exception e) {
            LOG.error("UUID [" + uuid + "] informed not found, error: "
                    + e.getMessage(), e);
        }
        return personDataTransferObject;
    }

    @Override
    public void delete(String uuid) {
        try {
            Optional<Person> optionalPerson = getOptionalPerson(uuid);

            Person person = PersonConvert.convertOptional(optionalPerson);

            personRepository.delete(person);
        } catch (Exception e) {
            LOG.error("UUID [" + uuid + "] informed not found, error: "
                    + e.getMessage(), e);
        }
    }

    @Override
    public void update(String uuid, PersonDataTransferObject personDTO) {
        try {
            Optional<Person> optionalPerson = getOptionalPerson(uuid);

            Person person = PersonConvert.convertOptional(optionalPerson);
            person.setBirthDate(LocalDate.parse(personDTO.getBirthDate()));
            person.setFirstName(personDTO.getFirstName());
            person.setLastName(personDTO.getLastName());
            personRepository.save(person);
        } catch (Exception e) {
            LOG.error("UUID [" + uuid + "] informed not found, error: "
                    + e.getMessage(), e);
        }

    }

    @Override
    public List<PersonDataTransferObject> findAll() {
        List<PersonDataTransferObject> personsDTO = new ArrayList<>();

        personRepository.findAll().stream().forEach(person -> {
            PersonDataTransferObject personDataTransferObject = new PersonDataTransferObject(
                    person.getFirstName(), person.getLastName(),
                    person.getBirthDate().toString());

            personsDTO.add(personDataTransferObject);
        });

        return personsDTO;
    }

    private Optional<Person> getOptionalPerson(String uuid) {
        return personRepository.findById(UUID.fromString(uuid));
    }

}
