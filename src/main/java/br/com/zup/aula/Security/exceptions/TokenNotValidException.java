package br.com.zup.aula.Security.exceptions;

public class TokenNotValidException extends RuntimeException {

    public TokenNotValidException(String message) {
        super(message);
    }
}
