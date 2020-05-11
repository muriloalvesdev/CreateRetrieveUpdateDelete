package br.com.crud.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.crud.dto.PersonDataTransferObject;

public interface PersonService {
  PersonDataTransferObject save(PersonDataTransferObject personDTO);

  PersonDataTransferObject find(String uuid);

  void delete(String uuid);

  void update(String uuid, PersonDataTransferObject personDTO);

  Page<PersonDataTransferObject> findAll(Pageable pageable);
}
