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
    public EmployeeDetailsResponseBody getDetails(Long id) throws ResourceNotFoundException{
        EmployeeDetailsResponseBody employeeDetailsResponseBody = new EmployeeDetailsResponseBody();
        AddressResponseBody addressResponseBody = new AddressResponseBody();
        Optional<EmployeeDetail> employeeDetailList = employeeRepository.findById(id);
        if (employeeDetailList.isPresent()) {
            Address address = employeeDetailList.get().getAddress();

            addressResponseBody.setId(address.getId());
            addressResponseBody.setAddressLine1(address.getAddressLine1());
            addressResponseBody.setAddressLine2(address.getAddressLine2());
            addressResponseBody.setCity(address.getCity());
            addressResponseBody.setState(address.getState());
            addressResponseBody.setPinCode(address.getPinCode());

            employeeDetailsResponseBody.setId(employeeDetailList.get().getId());
            employeeDetailsResponseBody.setEmpCode(employeeDetailList.get().getEmpCode());
            employeeDetailsResponseBody.setFirstName(employeeDetailList.get().getFirstName());
            employeeDetailsResponseBody.setLastName(employeeDetailList.get().getLastName());
            employeeDetailsResponseBody.setDesignation(employeeDetailList.get().getDesignation());
            employeeDetailsResponseBody.setNumber(employeeDetailList.get().getNumber());
            employeeDetailsResponseBody.setEmailId(employeeDetailList.get().getEmailId());
            employeeDetailsResponseBody.setAddressResponseBody(addressResponseBody);

            return employeeDetailsResponseBody;
        }else{
            throw new ResourceNotFoundException("Details is not present");
        }
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
}
