package com.Ecommerce.user_service.exception;

public class UserBlockedException extends RuntimeException{
    public UserBlockedException (String message){
        super(message);
    }
}
