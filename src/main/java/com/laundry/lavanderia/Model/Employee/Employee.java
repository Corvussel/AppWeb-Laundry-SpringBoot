package com.laundry.lavanderia.Model.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    
    private Long id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
    private String rol;
    private boolean estado; 
}