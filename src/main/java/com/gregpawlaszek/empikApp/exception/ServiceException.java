package com.gregpawlaszek.empikapp.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{
    private int statusCode;

    public ServiceException(String msg, int statusCode){
        super(msg);
        this.statusCode = statusCode;
    }
    
}
