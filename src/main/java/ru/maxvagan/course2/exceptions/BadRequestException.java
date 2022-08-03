package ru.maxvagan.course2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Неверные входные значения")
public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
