package com.elTomillo.demo.Servicios;

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

import com.elTomillo.demo.Entidades.Curso;
import com.elTomillo.demo.Entidades.Usuario;
import com.elTomillo.demo.Repositorios.CursoRepositorio;
import com.elTomillo.demo.Repositorios.UsuarioRepositorio;
import com.example.demo.errores.ErrorServicio;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

public class CursoService implements UserDetailsService {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private CursoRepositorio cursoRepositorio;

	@Transactional
	public Curso crearCurso(String nombre, Integer costo, Date alta, Date baja, Boolean activo) {
		Curso curso = new Curso();
		validar(nombre, costo);
		curso.setNombre(nombre);
		curso.setCosto(costo);
		curso.setAlta(LocalDate.now());
		curso.setBaja(curso.getAlta().plusYears(1));
		curso.setActivo(true);
		usuarioRepositorio.save(curso);
		return curso;

	}

	@Transactional
	public void validar(String nombre, Integer costo) throws ErrorServicio {

		if (nombre == null || nombre.isEmpty()) {
			throw new ErrorServicio("El nombre del curso no puede estar vacio o nulo");
		}
		if (costo == null) {
			throw new ErrorServicio("El costo del curso no puede estar vacio o nulo");
		}
	}

	public Curso modificarCurso(long id, String nombre, Integer costo, Boolean activo) throws ErrorServicio {

		Optional<Curso> respuesta = cursoRepositorio.findById(id);

		if (respuesta.isPresent()) {
			Curso curso = respuesta.get();
			curso.setNombre(nombre);
			curso.setCosto(costo);
			curso.setActivo(true);
			curso.setAlta(LocalDate.now());
			curso.setBaja(curso.getAlta().plusYears(1));
			cursoRepositorio.save(curso);

		} else {
			throw new ErrorServicio("No se encontro el curso solicitado");
		}
		return null;

	}

	public Curso eliminarCurso(long id) throws ErrorServicio {

		Optional<Curso> respuesta = cursoRepositorio.findById(id);

		if (respuesta.isPresent()) {
			Curso curso = respuesta.get();
			cursoRepositorio.delete(curso);
		} else {
			throw new ErrorServicio("No se encontro el usuario solicitado");
		}
		return null;
	}

	@Transactional
	public List<Curso> listarCursos() {
		List<Curso> listaCursos = cursoRepositorio.findAll();
		return listaCursos;

	}

	@Transactional
	public Curso findById(long id) throws ErrorServicio {
		Optional<Curso> respuesta = cursoRepositorio.findById(id);
		try {

			respuesta = cursoRepositorio.findById(id);
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
