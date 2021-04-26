package com.gregpawlaszek.empikapp.exception;

public class UserLoginArgumentException extends RuntimeException{
    public UserLoginArgumentException() {
        super("Illegal argument: null or blank String value passed");
    }
}
