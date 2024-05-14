package br.dev.andregurgel.linkshortener.api.config.handler;

import br.dev.andregurgel.linkshortener.api.config.exceptions.InvalidOrNotFoundShortenerCodeException;
import br.dev.andregurgel.linkshortener.api.config.handler.dto.RestErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidOrNotFoundShortenerCodeException.class)
    public ResponseEntity<RestErrorMessage> invalidOrNotFounfShortenerCodeExceptionHandler(InvalidOrNotFoundShortenerCodeException invalidOrNotFoundShortenerCodeException) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.NOT_FOUND, invalidOrNotFoundShortenerCodeException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
