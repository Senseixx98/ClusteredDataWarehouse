package com.example.DataWarehouse.exception;

import lombok.Getter;

@Getter
public class IdAlreadyExistException extends GenericException {
    public IdAlreadyExistException(String message) {
        super(message);
    }

}
