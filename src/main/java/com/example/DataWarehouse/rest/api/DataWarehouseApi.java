package com.example.DataWarehouse.rest.api;


import com.example.DataWarehouse.rest.dto.DataWarehouseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path= "/api")
public interface DataWarehouseApi {
    @PostMapping("/request")
    ResponseEntity<DataWarehouseDTO> request(@Valid @RequestBody DataWarehouseDTO dto);




}
