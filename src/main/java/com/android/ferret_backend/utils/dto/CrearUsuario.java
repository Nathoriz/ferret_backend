package com.android.ferret_backend.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CrearUsuario {
    private String nombre;
    private String apellido;
    private String direccion;
    private String email;
    private String contrasenia;
}
