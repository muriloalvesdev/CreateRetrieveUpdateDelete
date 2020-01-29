package br.com.crud.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.crud.domain.model.Person;
import br.com.crud.domain.repository.PersonRepository;
import br.com.crud.dto.PersonDataTransferObject;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void shouldSavePerson() {
        assertEquals(0, personRepository.findAll().size());

        PersonDataTransferObject personDataTransferObject = createObjectPersonDTO();
        personService.save(personDataTransferObject);

        assertEquals(1, personRepository.findAll().size());

        Person person = personRepository.findAll().get(0);

        assertEquals(personDataTransferObject.getFirstName(),
                person.getFirstName());
        assertEquals(personDataTransferObject.getLastName(),
                person.getLastName());
        assertEquals(personDataTransferObject.getBirthDate(),
                person.getBirthDate().toString());

        personRepository.deleteAll();
    }

    private PersonDataTransferObject createObjectPersonDTO() {
        return new PersonDataTransferObject("Murilo", "Alves",
                LocalDate.parse("2019-01-21").toString());
    }

}
