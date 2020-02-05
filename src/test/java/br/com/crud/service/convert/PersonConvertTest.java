package br.com.crud.service.convert;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.crud.domain.model.Person;
import br.com.crud.domain.repository.PersonRepository;
import br.com.crud.dto.PersonDataTransferObject;
import br.com.crud.service.exception.PersonNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonConvertTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void shouldConvertDataTransferObjectToEntity() {
        PersonDataTransferObject personDTO = new PersonDataTransferObject(
                "Murilo Alves", LocalDate.parse("1995-01-21").toString());

        Person person = PersonConvert.convert(personDTO);

        assertEquals("Murilo Alves", person.getFullName());
        assertEquals("1995-01-21", person.getBirthDate().toString());
    }

    @Test(expected = PersonNotFoundException.class)
    public void shouldReturnPersonException() throws PersonNotFoundException {
        Optional<Person> optionalPerson = personRepository.findById(
                UUID.fromString("5f7f2841-82bc-4536-8a49-0cf2cd59d64f"));

        PersonConvert.convertOptional(optionalPerson);
    }

    @Test
    public void shouldConvertOptionalPersonToPerson()
            throws PersonNotFoundException {

        // cria uma pessoa no banco de dados
        Person person = persistPerson();

        // busca essa pessoa pelo uuid/id
        Optional<Person> optionalPerson = personRepository
                .findById(person.getUuid());

        Person personConverted = PersonConvert.convertOptional(optionalPerson);

        assertEquals("Murilo Alves", personConverted.getFullName());
        assertEquals("1995-01-21", personConverted.getBirthDate().toString());
        assertEquals(person.getUuid(), personConverted.getUuid());

        personRepository.deleteAll();

    }

    @Test
    public void shouldConvertPersonEntityToPersonDataTransferObject() {
        Person person = persistPerson();

        PersonDataTransferObject personDataTransferObject = PersonConvert
                .convertToPatternDTO(person);

        assertEquals("Murilo Alves", personDataTransferObject.getFullName());
        assertEquals("1995-01-21",
                personDataTransferObject.getBirthDate().toString());

        personRepository.deleteAll();
    }

    private Person persistPerson() {
        PersonDataTransferObject personDTO = new PersonDataTransferObject(
                "Murilo Alves", LocalDate.parse("1995-01-21").toString());

        Person person = PersonConvert.convert(personDTO);
        return personRepository.saveAndFlush(person);
    }

}
