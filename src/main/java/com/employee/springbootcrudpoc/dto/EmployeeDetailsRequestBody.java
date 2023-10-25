package com.employee.springbootcrudpoc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class EmployeeDetailsRequestBody {

    private Long id;

    private String empCode;

    private String firstName;

    private String lastName;

    private String designation;

    private int number;

    private String emailId;

    private AddressRequestBody addressRequestBody;
}
