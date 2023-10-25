package com.employee.springbootcrudpoc.dto;

import jakarta.persistence.Column;
import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class AddressResponseBody {

    private Long id;

    @NotNull(message = "Please provide address Line 1")
    private String addressLine1;

    @NotNull(message = "Please provide address Line 2")
    private String addressLine2;

    @NotNull(message = "Please provide city")
    private String city;

    @NotNull(message = "Please provide state")
    private String state;

    @NotNull(message = "Please provide pinCode")
    private int pinCode;
}
