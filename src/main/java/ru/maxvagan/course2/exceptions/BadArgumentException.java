package ru.maxvagan.course2.exceptions;

public class BadArgumentException extends RuntimeException{
    public BadArgumentException(String message) {
        super(message);
    }
}
