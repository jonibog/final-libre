package org.ejemplo.exceptions;

import org.springframework.http.HttpStatus;

public class Tareaexception  extends Tareaexception{
    public Tareaexception(HttpStatus status, String message, String cause) {
        super(status, message, cause);
    }
}