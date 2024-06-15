package com.example.DataWarehouse.core.services;

import com.example.DataWarehouse.adapter.repo.DataWarehouseRepo;
import com.example.DataWarehouse.exception.CurrencyException;
import com.example.DataWarehouse.exception.IdAlreadyExistException;
import com.example.DataWarehouse.rest.dto.DataWarehouseDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class DataWarehouseServiceTest {
    DataWarehouseRepo dataWarehouseRepo = Mockito.mock(DataWarehouseRepo.class);
    DataWarehouseService dataWarehouseService =new DataWarehouseService(dataWarehouseRepo);

    @Test
    public void givenValidRequestWhenSaveRequestThenReturnValidDataWareHouseDto() {
        DataWarehouseDTO dataWarehouseDTO = dataWarehouseService.saveRequest(DataWarehouseDTO.builder()
                .id(1L)
                .fromCurrencyIso("JOD")
                .toCurrencyIso("JOD")
                .timestamp(Timestamp.from(Instant.now()))
                .dealAmount(BigDecimal.ONE)
                .build());

        Timestamp timestamp=dataWarehouseDTO.getTimestamp();
        assertEquals(dataWarehouseDTO.getId(),1);
        assertEquals(dataWarehouseDTO.getToCurrencyIso(),"JOD");
        assertEquals(dataWarehouseDTO.getFromCurrencyIso(),"JOD");
        assertEquals(dataWarehouseDTO.getTimestamp(),timestamp);
        assertEquals(dataWarehouseDTO.getDealAmount(),BigDecimal.ONE);

    }
    @Test
    public void givenValidRequestWithDataWarehouseRepoThrowExceptionWhenSaveRequestThenThrowException() {
        Mockito.doThrow(RuntimeException.class).when(dataWarehouseRepo).save(Mockito.any());
        assertThrows(RuntimeException.class,()-> dataWarehouseService.saveRequest(DataWarehouseDTO.builder()
                .id(1L)
                .fromCurrencyIso("JOD")
                .toCurrencyIso("JOD")
                .timestamp(Timestamp.from(Instant.now()))
                .dealAmount(BigDecimal.ONE)
                .build()));

    }

    @Test
    public void givenInValidRequestWithDataWarehouseRepoThrowIdAlreadyExistExceptionThenThrowExceptionWhenSaveRequest() {
        Mockito.when(dataWarehouseRepo.existsById(1L)).thenReturn(true);
        assertThrows(IdAlreadyExistException.class,()-> dataWarehouseService.saveRequest(DataWarehouseDTO.builder()
                .id(1L)
                .fromCurrencyIso("JOD")
                .toCurrencyIso("JOD")
                .timestamp(Timestamp.from(Instant.now()))
                .dealAmount(BigDecimal.ONE)
                .build()));

    }

    @Test
    public void givenInValidRequestWithDataWarehouseRepoThrowCurrencyExceptionThenThrowExceptionWhenSaveRequest() {
        Mockito.when(dataWarehouseRepo.existsById(1L)).thenReturn(true);
        assertThrows(CurrencyException.class,()-> dataWarehouseService.saveRequest(DataWarehouseDTO.builder()
                .id(4L)
                .fromCurrencyIso("BOT")
                .toCurrencyIso("JOD")
                .timestamp(Timestamp.from(Instant.now()))
                .dealAmount(BigDecimal.ONE)
                .build()));

    }
}