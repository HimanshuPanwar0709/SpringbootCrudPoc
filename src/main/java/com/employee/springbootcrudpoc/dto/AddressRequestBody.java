package com.employee.springbootcrudpoc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class AddressRequestBody {

    private Long id;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private int pinCode;
}
