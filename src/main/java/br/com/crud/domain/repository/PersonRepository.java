package br.com.crud.domain.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.crud.domain.model.Person;

public interface PersonRepository extends JpaRepository<Person, UUID> {
  Optional<Person> findByIdentifier(String identifier);
}
