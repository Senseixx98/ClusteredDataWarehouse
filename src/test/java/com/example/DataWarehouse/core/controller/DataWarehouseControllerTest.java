package com.example.DataWarehouse.core.controller;

import com.example.DataWarehouse.adapter.repo.DataWarehouseRepo;
import com.example.DataWarehouse.core.services.DataWarehouseService;
import com.example.DataWarehouse.rest.dto.DataWarehouseDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataWarehouseControllerTest {
    DataWarehouseService dataWarehouseServiceFake = Mockito.mock(DataWarehouseService.class);
    DataWarehouseController dataWarehouseController=new DataWarehouseController(dataWarehouseServiceFake);

    @Test
    public void test() {
        DataWarehouseDTO dataWarehouseDTO = DataWarehouseDTO.builder()
                .id(1L)
                .fromCurrencyIso("JOD")
                .toCurrencyIso("JOD")
                .timestamp(Timestamp.from(Instant.now()))
                .dealAmount(BigDecimal.ONE)
                .build();
        Mockito.when(dataWarehouseServiceFake.saveRequest(Mockito.any())).thenReturn(dataWarehouseDTO);
        ResponseEntity<DataWarehouseDTO> response = dataWarehouseController.request(dataWarehouseDTO);

        Timestamp timestamp=response.getBody().getTimestamp();
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(response.getBody().getId(),1);
        assertEquals(response.getBody().getToCurrencyIso(),"JOD");
        assertEquals(response.getBody().getFromCurrencyIso(),"JOD");
        assertEquals(response.getBody().getTimestamp(),timestamp);
        assertEquals(response.getBody().getDealAmount(),BigDecimal.ONE);

    }
    @Test
    public void testthrowExeception() {
        Mockito.doThrow(RuntimeException.class).when(dataWarehouseServiceFake).saveRequest(Mockito.any());
        assertThrows(RuntimeException.class,()->dataWarehouseController.request(DataWarehouseDTO.builder()
                .id(1L)
                .fromCurrencyIso("JOD")
                .toCurrencyIso("JOD")
                .timestamp(Timestamp.from(Instant.now()))
                .dealAmount(BigDecimal.ONE)
                .build()));

    }
}