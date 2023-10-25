package com.employee.springbootcrudpoc.controller;

import com.employee.springbootcrudpoc.dto.EmployeeDetailsRequestBody;
import com.employee.springbootcrudpoc.dto.EmployeeDetailsResponseBody;
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
    public EmployeeDetailsResponseBody getDetails(@RequestParam Long id)
    throws ResourceNotFoundException {
        return employeeService.getDetails(id);
    }

    @PostMapping(value = "/save-details")
    public ResponseEntity<?> saveDetails(
            @RequestBody EmployeeDetailsRequestBody employeeDetailsRequestBody
    )throws ResourceNotFoundException{
        return employeeService.saveDetails(employeeDetailsRequestBody);
    }

    @PutMapping(value = "/update-details-by-id")
    public ResponseEntity<?> updateDetailsById(
            @RequestParam Long id, @RequestBody EmployeeDetailsRequestBody employeeDetailsRequestBody
    )throws ResourceNotFoundException{
        return employeeService.updateDetailsById(id, employeeDetailsRequestBody);
    }


    @DeleteMapping(value = "/delete-details-by-id")
    public ResponseEntity<?> deleteDetailsById( @RequestParam Long id){
        return employeeService.deleteDetailsById(id);
    }
}
