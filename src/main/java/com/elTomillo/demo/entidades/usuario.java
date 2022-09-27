package com.elTomillo.demo.Entidades;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.elTomillo.demo.Util.Rol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String apellido;
	private String nombreEmpresa;
	private String email;
	private String password;
	@Temporal(TemporalType.TIMESTAMP)
	private Date nacimiento;
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDate alta;
	@Temporal(TemporalType.TIMESTAMP)
	private Date baja;
	private Boolean activo;
	@Enumerated(EnumType.STRING)
	private Rol rol;

}
