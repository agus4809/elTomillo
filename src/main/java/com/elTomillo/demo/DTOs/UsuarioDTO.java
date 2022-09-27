package com.elTomillo.demo.DTOs;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.elTomillo.demo.Util.Rol;

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
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombreEmpresa;
    @Email
    @NotBlank(message = "Debe indicar un email")
    private String email;
    @NotBlank(message = "Debe indicar una contraseña.")
    @Size(min = 8, message = "La contraseña no puede tener menos de 8 caracteres")
    private String password;
    @NotEmpty
    private LocalDate nacimiento;
    private Rol rol;

}