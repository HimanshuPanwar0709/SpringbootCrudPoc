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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDetailsResponseBody getDetails(Long id) throws ResourceNotFoundException {
        Optional<EmployeeDetail> employeeDetailOptional = employeeRepository.findById(id);
        EmployeeDetail employeeDetail = employeeDetailOptional.orElseThrow(() -> new ResourceNotFoundException("Details not found for ID: " + id));

        Address address = employeeDetail.getAddress();
        if (address == null) {
            throw new ResourceNotFoundException("Address not found for Employee ID: " + id);
        }
        EmployeeDetailsResponseBody employeeDetailsResponseBody = new EmployeeDetailsResponseBody();
        AddressResponseBody addressResponseBody = new AddressResponseBody();

        BeanUtils.copyProperties(address, addressResponseBody);
        employeeDetailsResponseBody.setAddressResponseBody(addressResponseBody);
        BeanUtils.copyProperties(employeeDetail, employeeDetailsResponseBody);

        return employeeDetailsResponseBody;
    }


    public ResponseEntity<?> saveDetails(EmployeeDetailsRequestBody employeeDetailsRequestBody)
            throws ResourceNotFoundException {

        EmployeeDetail employeeDetail = new EmployeeDetail();
        Address address = new Address();
        AddressRequestBody addressRequestBody = employeeDetailsRequestBody.getAddressRequestBody();

        mapEmployeeDetails(address, addressRequestBody,employeeDetail, employeeDetailsRequestBody);

        this.employeeRepository.save(employeeDetail);
        return ResponseEntity.status(HttpStatus.OK).body("Employee Detail save successfully");
    }

    @Override
    public ResponseEntity<?> updateDetailsById(Long id, EmployeeDetailsRequestBody employeeDetailsRequestBody) {
        try {
            Optional<EmployeeDetail> existingEmployeeOptional = employeeRepository.findById(id);
            if (existingEmployeeOptional.isPresent()) {
                EmployeeDetail employeeDetail = existingEmployeeOptional.get();
                AddressRequestBody addressRequestBody = employeeDetailsRequestBody.getAddressRequestBody();
                Address address = employeeDetail.getAddress();

                mapEmployeeDetails(address, addressRequestBody, employeeDetail, employeeDetailsRequestBody);

                employeeRepository.save(employeeDetail);

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

    private static void mapEmployeeDetails(
            Address address,
            AddressRequestBody addressRequestBody,
            EmployeeDetail employeeDetail,
            EmployeeDetailsRequestBody employeeDetailsRequestBody) {

        BeanUtils.copyProperties(addressRequestBody, address);
        employeeDetail.setAddress(address);
        BeanUtils.copyProperties(employeeDetailsRequestBody, employeeDetail);
    }
}
