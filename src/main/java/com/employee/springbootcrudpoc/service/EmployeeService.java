package com.employee.springbootcrudpoc.service;

import com.employee.springbootcrudpoc.dto.EmployeeDetailsRequestBody;
import com.employee.springbootcrudpoc.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {

    ResponseEntity<?> getDetails(Long id) throws ResourceNotFoundException;

    ResponseEntity<?> saveDetails(EmployeeDetailsRequestBody employeeDetailsRequestBody) throws ResourceNotFoundException;
}
