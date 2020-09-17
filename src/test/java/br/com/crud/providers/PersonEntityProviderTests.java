package br.com.crud.providers;

import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import br.com.crud.domain.model.Person;

public class PersonEntityProviderTests implements ArgumentsProvider, PersonConstants {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
    return Stream.of(Person.newBuilder().birthDate(LocalDate.parse(BIRTH_DATE)).fullName(FULL_NAME)
        .identifier(IDENTIFIER).build()).map(Arguments::of);
  }

}
