package br.com.crud.service.convert;

import java.time.LocalDate;
import java.util.Optional;

import br.com.crud.domain.model.Person;
import br.com.crud.dto.PersonDataTransferObject;
import br.com.crud.service.exception.PersonException;

public final class PersonConvert {

    public static Person convert(PersonDataTransferObject personDTO) {
        return new Person(personDTO.getFirstName(), personDTO.getLastName(),
                LocalDate.parse(personDTO.getBirthDate()));
    }

    public static Person convertOptional(Optional<Person> optionalPerson)
            throws PersonException {
        return optionalPerson
                .orElseThrow(() -> new PersonException("Person not found!"));
    }

    public static PersonDataTransferObject convertToPatternDTO(Person person) {
        return new PersonDataTransferObject(person.getFirstName(),
                person.getLastName(), person.getBirthDate().toString());
    }

}
