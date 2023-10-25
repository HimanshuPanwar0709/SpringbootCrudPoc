package com.employee.springbootcrudpoc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String empCode;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String designation;

    @Column
    private int number;

    @Column
    private String emailId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @JsonIgnore
    private Address address;


}
