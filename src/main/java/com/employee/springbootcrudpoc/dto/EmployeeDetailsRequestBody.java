package com.employee.springbootcrudpoc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class EmployeeDetailsRequestBody {


    private Long id;

    @NotNull(message = "Please provide Employee Code")
    private String empCode;

    @NotNull(message = "Please provide first Name")
    private String firstName;

    @NotNull(message = "Please provide last Name")
    private String lastName;

    @NotNull(message = "Please provide designation")
    private String designation;

    @NotNull(message = "Please provide number")
    @Digits(integer = 10, fraction = 0, message = "Number must be a 10-digit integer")
    private int number;

    @NotNull(message = "Please provide email Id")
    @Email(message = "Invalid email address")
    private String emailId;

    @Valid
    private AddressRequestBody addressRequestBody;
}
