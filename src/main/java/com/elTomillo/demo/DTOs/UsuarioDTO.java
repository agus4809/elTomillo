package com.elTomillo.demo.DTOs;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
    private long id;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;
    @Email
    @NotBlank(message = "Debe indicar un email")
    private String email;
    @NotBlank(message = "Debe indicar un numero de teléfono.")
    @Size(min = 0)
    private String telefono;
}