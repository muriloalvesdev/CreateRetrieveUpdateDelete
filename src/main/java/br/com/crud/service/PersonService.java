package br.com.crud.service;

import java.util.List;
import br.com.crud.dto.PersonDataTransferObject;

public interface PersonService {
  PersonDataTransferObject save(PersonDataTransferObject personDTO);

  PersonDataTransferObject find(String uuid);

  void delete(String uuid);

  void update(String uuid, PersonDataTransferObject personDTO);

  List<PersonDataTransferObject> findAll();
}
