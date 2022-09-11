package com.company.module.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO   {
 int status;
 String error;
 List<ErrorFieldDTO> fields;
 
}
