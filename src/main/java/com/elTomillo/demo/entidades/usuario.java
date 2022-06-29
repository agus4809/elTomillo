package com.elTomillo.demo.entidades;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;



import lombok.Data;


@Entity
@Data

public class usuario {

	@Id
	protected String id;
	protected String email;
	protected String password;
	
	
}
