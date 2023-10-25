package com.employee.springbootcrudpoc.dto;

import jakarta.persistence.Column;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class AddressResponseBody {

    private Long id;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private int pinCode;
}
