package br.dev.andregurgel.linkshortener.api.config.exceptions;

public class InvalidOrNotFoundShortenerCodeException extends RuntimeException {
    public InvalidOrNotFoundShortenerCodeException() { super("Não foi possível encontrar uma url válida para efetuar o redirecionamento.");}

    public InvalidOrNotFoundShortenerCodeException(String message) {
        super(message);
    }
}
