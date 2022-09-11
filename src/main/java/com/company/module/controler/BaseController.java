package com.company.module.controler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.FieldError;

import com.company.module.dto.ErrorFieldDTO;

public class BaseController {

    List<ErrorFieldDTO> toDTO(List<FieldError> fieldErrors) {
	return fieldErrors.stream()
		.map(a -> new ErrorFieldDTO(a.getField(), a.getDefaultMessage())).collect(Collectors.toList());
    }

}