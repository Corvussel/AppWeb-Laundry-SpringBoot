package com.laundry.lavanderia.Model.client;

 
import lombok.Data;

@Data 
public class cliente {
    
    private Long id;    
    private String nombre;
    private String telefono;
    private String direccion;
    private Boolean activo; 
    private String fechaRegistro;
}