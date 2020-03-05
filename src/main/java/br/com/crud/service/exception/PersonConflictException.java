package br.com.crud.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class PersonConflictException extends RuntimeException {

  private static final long serialVersionUID = -8942968518577729515L;

  public PersonConflictException(String message) {
    super(message);
  }

}
