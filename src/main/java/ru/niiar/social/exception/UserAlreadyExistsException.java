package ru.niiar.social.exception;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(String message){
        super(message);
    }

    public UserAlreadyExistsException(){}
}
