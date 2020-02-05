package br.com.crud.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends Exception {

    private static final long serialVersionUID = 7112832640247494726L;

    public PersonNotFoundException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public PersonNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonNotFoundException(String message) {
        super(message);
    }

    public PersonNotFoundException(Throwable cause) {
        super(cause);
    }

}
