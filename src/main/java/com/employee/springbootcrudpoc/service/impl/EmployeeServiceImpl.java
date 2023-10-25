package com.employee.springbootcrudpoc.service.impl;

import com.employee.springbootcrudpoc.dto.AddressRequestBody;
import com.employee.springbootcrudpoc.dto.AddressResponseBody;
import com.employee.springbootcrudpoc.dto.EmployeeDetailsRequestBody;
import com.employee.springbootcrudpoc.dto.EmployeeDetailsResponseBody;
import com.employee.springbootcrudpoc.exception.ResourceNotFoundException;
import com.employee.springbootcrudpoc.model.Address;
import com.employee.springbootcrudpoc.model.EmployeeDetail;
import com.employee.springbootcrudpoc.repository.EmployeeRepository;
import com.employee.springbootcrudpoc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public EmployeeDetailsResponseBody getDetails(Long id) throws ResourceNotFoundException {
        Optional<EmployeeDetail> employeeDetailOptional = employeeRepository.findById(id);
        EmployeeDetail employeeDetail = employeeDetailOptional.orElseThrow(() -> new ResourceNotFoundException("Details not found for ID: " + id));

        Address address = employeeDetail.getAddress();
        if (address == null) {
            throw new ResourceNotFoundException("Address not found for Employee ID: " + id);
        }

        AddressResponseBody addressResponseBody = new AddressResponseBody();
        addressResponseBody.setId(address.getId());
        addressResponseBody.setAddressLine1(address.getAddressLine1());
        addressResponseBody.setAddressLine2(address.getAddressLine2());
        addressResponseBody.setCity(address.getCity());
        addressResponseBody.setState(address.getState());
        addressResponseBody.setPinCode(address.getPinCode());

        EmployeeDetailsResponseBody employeeDetailsResponseBody = new EmployeeDetailsResponseBody();
        employeeDetailsResponseBody.setId(employeeDetail.getId());
        employeeDetailsResponseBody.setEmpCode(employeeDetail.getEmpCode());
        employeeDetailsResponseBody.setFirstName(employeeDetail.getFirstName());
        employeeDetailsResponseBody.setLastName(employeeDetail.getLastName());
        employeeDetailsResponseBody.setDesignation(employeeDetail.getDesignation());
        employeeDetailsResponseBody.setNumber(employeeDetail.getNumber());
        employeeDetailsResponseBody.setEmailId(employeeDetail.getEmailId());
        employeeDetailsResponseBody.setAddressResponseBody(addressResponseBody);

        return employeeDetailsResponseBody;
    }


    public ResponseEntity<?> saveDetails(EmployeeDetailsRequestBody employeeDetailsRequestBody)
            throws ResourceNotFoundException {

        EmployeeDetail employeeDetail = new EmployeeDetail();
        Address address = new Address();
        AddressRequestBody addressRequestBody = employeeDetailsRequestBody.getAddressRequestBody();

        address.setId(addressRequestBody.getId());
        address.setAddressLine1(addressRequestBody.getAddressLine1());
        address.setAddressLine2(addressRequestBody.getAddressLine2());
        address.setCity(addressRequestBody.getCity());
        address.setState(addressRequestBody.getState());
        address.setPinCode(addressRequestBody.getPinCode());

        employeeDetail.setId(employeeDetailsRequestBody.getId());
        employeeDetail.setEmpCode(employeeDetailsRequestBody.getEmpCode());
        employeeDetail.setFirstName(employeeDetailsRequestBody.getFirstName());
        employeeDetail.setLastName(employeeDetailsRequestBody.getLastName());
        employeeDetail.setDesignation(employeeDetailsRequestBody.getDesignation());
        employeeDetail.setNumber(employeeDetailsRequestBody.getNumber());
        employeeDetail.setEmailId(employeeDetailsRequestBody.getEmailId());
        employeeDetail.setAddress(address);

        this.employeeRepository.save(employeeDetail);
        return ResponseEntity.status(HttpStatus.OK).body("Employee Detail save successfully");
    }

    @Override
    public ResponseEntity<?> updateDetailsById(Long id, EmployeeDetailsRequestBody employeeDetailsRequestBody) {
        try {
            Optional<EmployeeDetail> existingEmployeeOptional = employeeRepository.findById(id);
            if (existingEmployeeOptional.isPresent()) {
                EmployeeDetail existingEmployee = existingEmployeeOptional.get();
                
                AddressRequestBody addressRequestBody = employeeDetailsRequestBody.getAddressRequestBody();
                Address address = existingEmployee.getAddress();
                address.setId(addressRequestBody.getId());
                address.setAddressLine1(addressRequestBody.getAddressLine1());
                address.setAddressLine2(addressRequestBody.getAddressLine2());
                address.setCity(addressRequestBody.getCity());
                address.setState(addressRequestBody.getState());
                address.setPinCode(addressRequestBody.getPinCode());
                
                existingEmployee.setEmpCode(employeeDetailsRequestBody.getEmpCode());
                existingEmployee.setFirstName(employeeDetailsRequestBody.getFirstName());
                existingEmployee.setLastName(employeeDetailsRequestBody.getLastName());
                existingEmployee.setDesignation(employeeDetailsRequestBody.getDesignation());
                existingEmployee.setNumber(employeeDetailsRequestBody.getNumber());
                existingEmployee.setEmailId(employeeDetailsRequestBody.getEmailId());

                // Save the updated employee details
                employeeRepository.save(existingEmployee);

                return ResponseEntity.status(HttpStatus.OK).body("Employee Detail updated successfully");
            } else {
                throw new ResourceNotFoundException("Employee not found with ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update employee details");
        }
    }

    @Override
    public ResponseEntity<?> deleteDetailsById(Long id) {
        try {
            Optional<EmployeeDetail> employeeOptional = employeeRepository.findById(id);
            if (employeeOptional.isPresent()) {
                employeeRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body("Employee with ID: " + id + " deleted successfully");
            } else {
                throw new ResourceNotFoundException("Employee not found with ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete employee with ID: " + id);
        }
    }
}