package com.example.DataWarehouse.rest.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Data
@Builder
@Validated
public class DataWarehouseDTO {


    @NotNull
    // @NotNull(message = "Id already exist")
    private Long id;
    @NotNull
    //   @Size(min = 3, max = 3 ,message = "Currency Iso must be 3 characters")
    @Pattern(regexp = "^[A-Za-z]{3}$", message = "fromCurrencyIso field numbers or special characters are not allowed and length must be 3 characters")
    private String fromCurrencyIso;
    @NotNull
    //  @Size(min = 3, max = 3 ,message = "Currency Iso must be  3 characters")
    @Pattern(regexp = "^[A-Za-z]{3}$", message = "toCurrencyIso field numbers or special characters are not allowed and length must be 3 characters")
    private String toCurrencyIso;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Timestamp timestamp;
    @NotNull
    @Min(0)
    private BigDecimal dealAmount;
}
