package com.android.ferret_backend.utils.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Login implements Serializable {
    private String email;
    private String contrasenia;
}
