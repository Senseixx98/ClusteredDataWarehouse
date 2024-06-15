package com.example.DataWarehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.example.DataWarehouse"})
@ComponentScan
public class DataWarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataWarehouseApplication.class, args);
	}

}
