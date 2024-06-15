package com.example.DataWarehouse.handler;

import com.example.DataWarehouse.exception.GenericException;
import com.example.DataWarehouse.rest.dto.ExceptionObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Handlers {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionObject> handleInvalidInput(MethodArgumentNotValidException e) {
        ExceptionObject exceptionObject = new ExceptionObject();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            exceptionObject.getMessages().add("key : " + ((FieldError) error).getField()+ " " +error.getDefaultMessage());
        });
        return new ResponseEntity<>(exceptionObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ExceptionObject> handleExceptionWithLogs(GenericException e) {
        ExceptionObject exceptionObject = new ExceptionObject();
        exceptionObject.getMessages().add(e.getMessage());

        return new ResponseEntity<>(exceptionObject,HttpStatus.BAD_REQUEST);
    }



}
