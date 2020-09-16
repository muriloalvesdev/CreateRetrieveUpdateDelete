package br.com.crud.dto;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Builder(builderMethodName = "newBuilder")
public class PersonDataTransferObject {

  @JsonProperty("full_name")
  @NotNull
  private String fullName;

  @JsonProperty("birth_date")
  @NotNull
  private String birthDate;

  @JsonProperty("identifier")
  @NotNull
  private String identifier;

}
