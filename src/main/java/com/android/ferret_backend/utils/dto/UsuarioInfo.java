package com.android.ferret_backend.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioInfo {
    private Long id;
    private String nombre;
    private String apellido;
    private String direccion;
}
