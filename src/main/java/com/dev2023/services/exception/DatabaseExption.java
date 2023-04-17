package com.dev2023.services.exception;

public class DatabaseExption extends  RuntimeException{

    public DatabaseExption(String message) {
        super(message);
    }
}


