package com.axonactive.khoa.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Company {
    private Long id;
    private String companyName;
    private String address;
    private List<Employee> employees;
}
