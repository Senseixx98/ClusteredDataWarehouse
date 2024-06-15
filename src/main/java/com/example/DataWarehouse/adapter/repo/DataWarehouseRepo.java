package com.example.DataWarehouse.adapter.repo;

import com.example.DataWarehouse.adapter.entities.DataWarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataWarehouseRepo extends JpaRepository<DataWarehouseEntity,Long> {
}

