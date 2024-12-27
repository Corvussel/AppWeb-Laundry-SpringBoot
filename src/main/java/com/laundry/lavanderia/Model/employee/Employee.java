package com.laundry.lavanderia.Model.employee;

import lombok.Data;

@Data
public class Employee {
    private Long id;
    
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String role;
    private String status;
    private String password;
    private String profileImage;

    public Employee() {
    }
    
    public Employee(Long id, String firstName, String lastName, String email, String phone, String role, String status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.status = status;
    }

    public Employee(String firstName, String lastName, String email, String phone, String role, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.status = status;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
 
}
