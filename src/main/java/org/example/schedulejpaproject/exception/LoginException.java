package org.example.schedulejpaproject.exception;

import lombok.Getter;

@Getter
public class LoginException extends RuntimeException{
    public LoginException(String msg) {
        super(msg);
    }
}
