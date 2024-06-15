package com.example.DataWarehouse.rest.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExceptionObject {
    List<String> messages = new ArrayList<>();
}
