package com.elTomillo.demo.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.elTomillo.demo.enums.Rol;

import lombok.Data;
@Entity
@Data

public class registro {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;
	protected String name;
	protected String lastName;
	protected String businessname;
	protected String email;
	protected String password;	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date nacimiento; 
	@Temporal(TemporalType.TIMESTAMP)
	protected Date alta;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date baja;
	protected Boolean activo;
	@Enumerated(EnumType.STRING)
	protected Rol rol;
	
	
}
