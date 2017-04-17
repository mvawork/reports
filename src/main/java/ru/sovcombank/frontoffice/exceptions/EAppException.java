package ru.sovcombank.frontoffice.exceptions;

public class EAppException extends RuntimeException {

    public EAppException(String message) {
        super(message);
    }

    public EAppException(String message, Throwable cause) {
        super(message, cause);
    }

}
