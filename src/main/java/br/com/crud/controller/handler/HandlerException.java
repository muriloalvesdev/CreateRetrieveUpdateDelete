package br.com.crud.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import br.com.crud.domain.model.ApiException;
import br.com.crud.service.exception.PersonConflictException;
import br.com.crud.service.exception.PersonNotFoundException;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

  @ExceptionHandler(PersonConflictException.class)
  public @ResponseBody ResponseEntity<ApiException> handlePersonConflictException(
      PersonConflictException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(createExceptionReturn(ex, HttpStatus.CONFLICT.value()));
  }

  @ExceptionHandler(PersonNotFoundException.class)
  public @ResponseBody ResponseEntity<ApiException> handlePersonNotFoundException(
      PersonNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(createExceptionReturn(ex, HttpStatus.NOT_FOUND.value()));
  }

  private ApiException createExceptionReturn(RuntimeException ex, int value) {
    return new ApiException(ex.getMessage(), value);
  }

}
