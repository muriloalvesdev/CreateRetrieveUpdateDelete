package br.com.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.crud.dto.PersonDataTransferObject;
import br.com.crud.service.PersonService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/person")
public class PersonController {

  @Autowired
  private PersonService personService;

  @PostMapping("save")
  public ResponseEntity<PersonDataTransferObject> save(
      @Validated @RequestBody PersonDataTransferObject personDTO) {
    personService.save(personDTO);
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/api/person/find/{identifier}").buildAndExpand(personDTO.getIdentifier()).toUri())
        .build();
  }

  @GetMapping("find/{identifier}")
  public ResponseEntity<PersonDataTransferObject> find(
      @PathVariable(name = "identifier") String identifier) {
    return ResponseEntity.ok(personService.find(identifier));
  }

  @GetMapping("find")
  public ResponseEntity<Page<PersonDataTransferObject>> findAll(Pageable pageable) {
    return ResponseEntity.ok(personService.findAll(pageable));
  }

  @DeleteMapping("delete/{identifier}")
  public ResponseEntity<Void> delete(@PathVariable(name = "identifier") String uuid) {
    personService.delete(uuid);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("update/{identifier}")
  public ResponseEntity<Void> update(
      @PathVariable(name = "identifier", required = true) String uuid,
      @Validated @RequestBody PersonDataTransferObject personDTO) {
    personService.update(uuid, personDTO);
    return ResponseEntity.noContent().build();
  }
}
