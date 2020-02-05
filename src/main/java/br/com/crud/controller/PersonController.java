package br.com.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.crud.domain.model.Person;
import br.com.crud.dto.PersonDataTransferObject;
import br.com.crud.service.PersonService;

@RestController
@RequestMapping("api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("save")
    public ResponseEntity<Person> save(
            @RequestBody PersonDataTransferObject personDTO) {
        Person person = personService.save(personDTO);

        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/person/find/{uuid}")
                .buildAndExpand(person.getUuid()).toUri()).build();
    }

    @GetMapping("find/{uuid}")
    public ResponseEntity<PersonDataTransferObject> find(
            @PathVariable(name = "uuid") String uuid) {
        return ResponseEntity.ok(personService.find(uuid));
    }

    @GetMapping("find")
    public ResponseEntity<List<PersonDataTransferObject>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @DeleteMapping("delete/{uuid}")
    public ResponseEntity<Void> delete(
            @PathVariable(name = "uuid") String uuid) {
        personService.delete(uuid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("update/{uuid}")
    public ResponseEntity<Void> update(
            @PathVariable(name = "uuid", required = true) String uuid,
            @RequestBody PersonDataTransferObject personDTO) {
        personService.update(uuid, personDTO);
        return ResponseEntity.noContent().build();
    }

}
