package com.employee.springbootcrudpoc.controller;

import com.employee.springbootcrudpoc.dto.EmployeeDetailsRequestBody;
import com.employee.springbootcrudpoc.exception.ResourceNotFoundException;
import com.employee.springbootcrudpoc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/get-details")
    public ResponseEntity<?> getDetails(@RequestParam Long id)
    throws ResourceNotFoundException {
        return employeeService.getDetails(id);
    }

    @PostMapping(value = "/save-details")
    public ResponseEntity<?> saveDetails(
            @RequestBody EmployeeDetailsRequestBody employeeDetailsRequestBody
    )throws ResourceNotFoundException{
        return employeeService.saveDetails(employeeDetailsRequestBody);
    }
}
