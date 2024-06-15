package com.example.DataWarehouse.core.controller;

import com.example.DataWarehouse.core.services.DataWarehouseService;
import com.example.DataWarehouse.exception.GenericException;
import com.example.DataWarehouse.rest.api.DataWarehouseApi;
import com.example.DataWarehouse.rest.dto.DataWarehouseDTO;
import com.example.DataWarehouse.rest.dto.ExceptionObject;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DataWarehouseController implements DataWarehouseApi {

    private final DataWarehouseService dataWarehouseService;

    public DataWarehouseController(DataWarehouseService dataWarehouseService) {
        this.dataWarehouseService = dataWarehouseService;
    }

    @Override
    public ResponseEntity<DataWarehouseDTO> request(DataWarehouseDTO dto) {
        log.info("Received request with DTO: {}", dto);
        DataWarehouseDTO dataWarehouseDTO = dataWarehouseService.saveRequest(dto);
        log.info("Request processed successfully");
        return new ResponseEntity<>(dataWarehouseDTO, HttpStatus.CREATED);
    }


}

