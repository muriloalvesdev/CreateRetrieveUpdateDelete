package br.com.crud.service.exception;

public class PersonConflictException extends RuntimeException {

  private static final long serialVersionUID = -8942968518577729515L;

  public PersonConflictException(String message) {
    super(message);
  }

}
