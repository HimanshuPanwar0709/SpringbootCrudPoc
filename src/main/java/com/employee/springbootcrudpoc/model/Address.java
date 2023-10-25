package com.employee.springbootcrudpoc.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String addressLine1;

    @Column
    private String addressLine2;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private int pinCode;
}
