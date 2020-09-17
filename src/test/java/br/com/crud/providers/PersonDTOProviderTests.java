package br.com.crud.providers;

import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import br.com.crud.dto.PersonDataTransferObject;

public class PersonDTOProviderTests implements ArgumentsProvider, PersonConstants {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
    return Stream
        .of(PersonDataTransferObject.newBuilder().birthDate(LocalDate.parse(BIRTH_DATE).toString())
            .identifier(IDENTIFIER).fullName(FULL_NAME).build())
        .map(Arguments::of);
  }

}
