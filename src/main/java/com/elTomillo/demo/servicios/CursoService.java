package com.elTomillo.demo.servicios;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.elTomillo.demo.entidades.Curso;
import com.elTomillo.demo.entidades.Usuario;
import com.elTomillo.demo.repositorios.registroRepositorio;
import com.example.demo.errores.ErrorServicio;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

public class CursoService implements UserDetailsService{

	@Autowired
	private registroRepositorio UsuarioRepositorio;
	
	@Transactional
	public Curso crearCurso(String nombre, Integer costo, Date alta, Date baja, Boolean activo) {
		Curso curso = new Curso();
		validar(nombre, costo);
		curso.setNombre(nombre);
		curso.setCosto(costo);
		curso.setAlta(LocalDate.now());		
		curso.setBaja(curso.getAlta().plusYears(1));
		curso.setActivo(true);
		UsuarioRepositorio.save(curso);
		return curso;
		
	}
	@Transactional
	public void validar(String nombre, Integer costo)throws ErrorServicio {
		
		if (nombre ==null || nombre.isEmpty() ) {
			throw new ErrorServicio("El nombre del curso no puede estar vacio o nulo");
		}
		if (costo == null) {
			throw new ErrorServicio("El costo del curso no puede estar vacio o nulo");
		}
	}
	
	public Curso modificarCurso(String id,String nombre, Integer costo, Boolean activo) {
		
		Optional<Curso> respuesta = CursoRepositorio.findById(id);
		
		if (respuesta.isPresent()) {
			Curso curso = respuesta.get();
			curso.setNombre(nombre);
			curso.setCosto(costo);
			curso.setActivo(true);
			curso.setAlta(LocalDate.now());		
			curso.setBaja(curso.getAlta().plusYears(1));
			CursoRepositorio.save(curso);
			
		}else {
			throw new ErrorServicio("No se encontro el curso solicitado");
		}
		return null;
		
	}
		public Curso eliminarCurso(String id) {
		
		Optional<Curso> respuesta = CursoRepositorio.findById(id);
		
		if (respuesta.isPresent()) {
			Curso curso = respuesta.get();
			cursoRepositorio.delete(curso);
		}else {
			throw new ErrorServicio("No se encontro el usuario solicitado");
		}
	}
		
		@Transactional
		public List<Curso>listarCursos() {
			List <Curso> listaCursos = CursoRepositorio.findAll();
			return listaCursos;
			
		}
		
		@Transactional
		public Curso findById(String id) throws ErrorServicio {
			Optional<Curso> respuesta = CursoRepositorio.findById(id);
			try {
				
				respuesta = CursoRepositorio.findById(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (respuesta.isPresent()) {
				Curso curso = respuesta.get();
				return curso;
			} else {
				throw new ErrorServicio("No se encontro el curso solicitado");
			}
		}
		
		
		
		
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	
	
}
