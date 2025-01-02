package com.laundry.lavanderia.Model.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import com.laundry.lavanderia.Model.Security.Role;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    @ManyToOne
    private Role role;
    private Boolean status;
    private String password;
    private String profileImage;

    public Employee() {
    }

    public Employee(Long id, String firstName, String lastName, String email, String phone, Role role,
    Boolean status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.status = status;
    }

    public Employee(String firstName, String lastName, String email, String phone, Role role, Boolean status) {
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
