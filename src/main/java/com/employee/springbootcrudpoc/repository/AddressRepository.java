package com.employee.springbootcrudpoc.repository;

import com.employee.springbootcrudpoc.model.Address;
import com.employee.springbootcrudpoc.model.EmployeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
