package com.employee.springbootcrudpoc.dto;

import com.employee.springbootcrudpoc.model.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EmployeeDetailsResponseBody {
    private Long id;

    private String empCode;

    private String firstName;

    private String lastName;

    private String designation;

    private int number;

    private String emailId;

    private AddressResponseBody addressResponseBody;

}
