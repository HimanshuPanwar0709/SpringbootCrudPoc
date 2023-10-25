package com.employee.springbootcrudpoc.service;

import com.employee.springbootcrudpoc.dto.EmployeeDetailsRequestBody;
import com.employee.springbootcrudpoc.dto.EmployeeDetailsResponseBody;
import com.employee.springbootcrudpoc.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {

    EmployeeDetailsResponseBody getDetails(Long id) throws ResourceNotFoundException;

    ResponseEntity<?> saveDetails(EmployeeDetailsRequestBody employeeDetailsRequestBody) throws ResourceNotFoundException;

    ResponseEntity<?> updateDetailsById(Long id, EmployeeDetailsRequestBody employeeDetailsRequestBody);

    ResponseEntity<?> deleteDetailsById(Long id);
}
