package com.gregpawlaszek.empikApp.exception;

public class ExternalApiException extends RuntimeException{
    public ExternalApiException(){
        super("Couldn't connect to external API.");
    }
}
