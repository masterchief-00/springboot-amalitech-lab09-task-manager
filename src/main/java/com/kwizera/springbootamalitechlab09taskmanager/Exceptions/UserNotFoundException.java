package com.kwizera.springbootamalitechlab09taskmanager.Exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
