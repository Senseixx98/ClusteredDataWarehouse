package com.example.DataWarehouse.handler;

import com.example.DataWarehouse.exception.GenericException;
import com.example.DataWarehouse.rest.dto.ExceptionObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class HandlersTest {

    @Test
    public void givenValidRequestWhenHandleExceptionWithLogsThenReturnCorrectResponse() {
        Handlers handlers = new Handlers();
        ResponseEntity<ExceptionObject> response = handlers.handleExceptionWithLogs(new GenericException("test"));
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(1, response.getBody().getMessages().size());
        assertEquals(response.getBody().getMessages().get(0), "test");
    }


    @Test
    public void givenValidRequestWhenHandleInvalidInputThenReturnCorrectResponse() {
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        FieldError fieldError = new FieldError("objectName", "field", "must not be null");
        Mockito.when(bindingResult.getAllErrors()).thenReturn(Collections.singletonList(fieldError));
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);
        Handlers handler = new Handlers();
        ResponseEntity<ExceptionObject> responseEntity = handler.handleInvalidInput(exception);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        ExceptionObject exceptionObject = responseEntity.getBody();
        assertEquals(1, exceptionObject.getMessages().size());
        assertEquals("key : field must not be null", exceptionObject.getMessages().get(0));
    }

}