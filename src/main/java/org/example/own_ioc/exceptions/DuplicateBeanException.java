package org.example.own_ioc.exceptions;

public class DuplicateBeanException extends RuntimeException {
    public DuplicateBeanException(String message) {
        super(message);
    }
}
