package com.elTomillo.demo.Servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.elTomillo.demo.DTOs.UsuarioDTO;
import com.elTomillo.demo.Entidades.Usuario;
import com.elTomillo.demo.Mappers.UsuarioMapper;
import com.elTomillo.demo.Repositorios.UsuarioRepositorio;
import com.example.demo.errores.ErrorServicio;

public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private UsuarioMapper mapper;

	@Transactional
	public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) throws ErrorServicio {
		Usuario usuarioReg = mapper.DTOaEntidad(usuarioDTO);
		usuarioRepositorio.save(usuarioReg);
		usuarioReg.setAlta(LocalDate.now());
		usuarioReg.setActivo(true);
		UsuarioDTO dtoReg = mapper.EntidadaDTO(usuarioReg);
		return dtoReg;
	}

	@Transactional
	public void validar(String name, String lastName, String businessname, String email, String password)
			throws ErrorServicio {

	}

	@Transactional
	public void modificarUsuario(long id, String name, String lastName, String businessname,
			String email, String password) throws ErrorServicio {
		validar(name, lastName, businessname, email, password);

		Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
		try {
			respuesta = usuarioRepositorio.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (respuesta.isPresent()) {

			Usuario reg = respuesta.get();

			reg.setNombre(name);
			reg.setApellido(lastName);
			reg.setEmail(email);
			String encriptada = new BCryptPasswordEncoder().encode(password);
			reg.setPassword(encriptada);
			usuarioRepositorio.save(reg);
		} else {
			throw new ErrorServicio("No se encontro el usuario solicitado");
		}

	}

	public void EliminarRegistro(long id) throws ErrorServicio {
		Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

		if (respuesta.isPresent()) {

			Usuario reg = respuesta.get();

			usuarioRepositorio.delete(reg);

		} else {
			throw new ErrorServicio("No se encontro el usuario solicitado");
		}
	}

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Usuario reg = usuarioRepositorio.buscarPorEmail(email);

		if (reg != null) {

			List<GrantedAuthority> permisos = new ArrayList<>();

			// Creo una lista de permisos!
			GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + reg.getRol());
			permisos.add(p1);

			// Esto me permite guardar el OBJETO USUARIO LOGUEADO, para luego ser utilizado
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true);

			session.setAttribute("usuariosession", reg); // llave + valor

			User user = new User(reg.getEmail(), reg.getPassword(), permisos);

			return user;

		} else {
			return null;
		}
	}

	@Transactional
	public List<Usuario> listarRegistrados() {
		List<Usuario> xx = usuarioRepositorio.findAll();
		return xx;

	}

	@Transactional
	public Usuario findUserByEmail(String email, String password) throws ErrorServicio {
		Usuario reg = usuarioRepositorio.buscarPorEmail(email);
		if (reg != null) {
			return validarUsuario(reg, password);
		} else {
			throw new ErrorServicio("No existe el registro.");
		}
	}

	private Usuario validarUsuario(Usuario reg, String password) throws ErrorServicio {
		if (reg.getPassword().equals(password)) {
			return reg;
		} else {
			throw new ErrorServicio("Contraseña incorrecta");
		}
	}

	@Transactional
	public Usuario findById(long id) throws ErrorServicio {
		Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
		try {
			respuesta = usuarioRepositorio.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (respuesta.isPresent()) {
			Usuario reg = respuesta.get();
			return reg;
		} else {
			throw new ErrorServicio("No se encontro el registro solicitado");
		}
	}

}
