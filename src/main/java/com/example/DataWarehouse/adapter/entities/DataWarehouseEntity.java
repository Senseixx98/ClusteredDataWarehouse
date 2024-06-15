package com.example.DataWarehouse.adapter.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Currency;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "data")

public class DataWarehouseEntity {
    @Id
    @Column(name = "transfer_id")
    private Long id;
    private Currency fromCurrency;
    private Currency toCurrency;
    private Timestamp timestamp;
    private BigDecimal dealAmount;

}
