package com.elTomillo.demo.entidades;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;



import lombok.Data;


@Entity
@Data

public class Curso {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;
	protected String nombre;
	protected Integer costo;
	@Temporal(TemporalType.TIMESTAMP)
	protected LocalDate alta;
	@Temporal(TemporalType.TIMESTAMP)
	protected LocalDate baja;
	protected Boolean activo;
	
	
	
}
