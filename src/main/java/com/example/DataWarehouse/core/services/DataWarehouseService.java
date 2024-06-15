package com.example.DataWarehouse.core.services;

import com.example.DataWarehouse.exception.CurrencyException;
import com.example.DataWarehouse.exception.IdAlreadyExistException;
import com.example.DataWarehouse.adapter.entities.DataWarehouseEntity;
import com.example.DataWarehouse.adapter.repo.DataWarehouseRepo;
import com.example.DataWarehouse.rest.dto.DataWarehouseDTO;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Currency;

@Slf4j
@Service
public class DataWarehouseService {
    private final DataWarehouseRepo dataWarehouseRepo;


    public DataWarehouseService(DataWarehouseRepo dataWarehouseRepo) {
        this.dataWarehouseRepo = dataWarehouseRepo;
    }


    public DataWarehouseDTO saveRequest(DataWarehouseDTO dto) {
        log.info("Saving request with DTO: {}", dto);
        dto.setTimestamp(Timestamp.from(Instant.now()));
        validateRequest(dto);
        var dataWarehouse = DataWarehouseEntity
                .builder()
                .id(dto.getId())
                .fromCurrency(Currency.getInstance(dto.getFromCurrencyIso().toUpperCase()))
                .toCurrency(Currency.getInstance(dto.getToCurrencyIso().toUpperCase()))
                .timestamp(dto.getTimestamp())
                .dealAmount(dto.getDealAmount())
                .build();
        dataWarehouseRepo.save(dataWarehouse);


        return dto;
    }


    private void validateRequest(DataWarehouseDTO dto) {
        log.info("Validating request with DTO: {}", dto);
        if (dataWarehouseRepo.existsById(dto.getId())) {
            log.warn("Request with ID" + dto.getId() + "already exist");
            throw new IdAlreadyExistException("Request with ID " + dto.getId() + " already exist");
        }
        validateCurrency(dto.getFromCurrencyIso().toUpperCase());
        validateCurrency(dto.getToCurrencyIso().toUpperCase());

        log.info("Request validated and saved successfully");
    }


    private void validateCurrency(String currencyIso) {
        try {
            Currency.getInstance(currencyIso);
        } catch (IllegalArgumentException e) {
            log.warn("The currency ISO " + currencyIso + " not found");
            throw new CurrencyException("The currency ISO " + currencyIso + " not found");
        }
    }





}
