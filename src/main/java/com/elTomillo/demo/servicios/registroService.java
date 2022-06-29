package com.elTomillo.demo.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

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

import com.elTomillo.demo.entidades.registro;

import com.elTomillo.demo.repositorios.registroRepositorio;

import com.example.demo.errores.ErrorServicio;


import ch.qos.logback.core.boolex.Matcher;

public class registroService implements UserDetailsService {

	@Autowired
	private registroRepositorio registroRepositorio;
	
	@Transactional
	public registro crearRegistro(String name, String lastName,String businessname,
			String email, String password, Date alta, Boolean activo) throws ErrorServicio  {
		 
			validar(name, lastName,businessname, email, password);
		
		
		registro reg = new registro();
		
		reg.setName(name);
		reg.setLastName(lastName);
		reg.setBusinessname(businessname);	
		reg.setEmail(email);
		String encriptada = new BCryptPasswordEncoder().encode(password);
		reg.setPassword(encriptada);
		reg.setAlta(alta);
		reg.setActivo(activo);
		registroRepositorio.save(reg);
			
		return reg;
}

	@Transactional
	public void validar(String name, String lastName, String businessname, String email, String password)throws ErrorServicio {
		
		if (name ==null || name.isEmpty() ) {
			throw new ErrorServicio("El nombre del usuario no puede estar vacio o nulo");
		}
		if (lastName == null || lastName.isEmpty()) {
			throw new ErrorServicio("El apellido del usuario no puede estar vacio o nulo");
		}
		if (businessname==null || businessname.isEmpty()) {
			throw new ErrorServicio("El nombre del comercio no puede estar vacio o nulo");
		}
		if (email==null || email.isEmpty()) {
			throw new ErrorServicio("debe ingresar un email valido");
		}else {
			Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			
			java.util.regex.Matcher mather = pattern.matcher(email);

				if (mather.find() == true) {
				System.out.println("El email ingresado es válido.");
				} else {
					throw new ErrorServicio("el email es invalido");
				}
		}
		if (password == null || password.isEmpty()) {
			throw new ErrorServicio("debe ingresar un password valido");
		}
		if (password.length()< 8) {
			throw new ErrorServicio("password debe tener 8 o mas caracteres ");
		}
		
		
		
	}
	@Transactional
	public void modificarUsuario(String id, String name, String lastName,String businessname,
			String email, String password, Date alta, Boolean activo) throws ErrorServicio {
		validar(name,  lastName,  businessname,  email,  password);
		
		Optional<registro> respuesta =registroRepositorio.findById(id);;
		try {
			respuesta = registroRepositorio.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (respuesta.isPresent()) {

			registro reg = respuesta.get();

			reg.setName(name);
			reg.setLastName(lastName);
			reg.setEmail(email);
			String encriptada = new BCryptPasswordEncoder().encode(password);
			reg.setPassword(encriptada);

			registroRepositorio.save(reg);
		}else {
			throw new ErrorServicio("No se encontro el usuario solicitado");
		}
		
	}
	
	public void EliminarRegistro(String id) throws ErrorServicio {
		Optional<registro> respuesta = registroRepositorio.findById(id);

		if (respuesta.isPresent()) {

			registro reg = respuesta.get();

			registroRepositorio.delete(reg);

		} else {
			throw new ErrorServicio("No se encontro el usuario solicitado");
		}
	}
	
		public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	
        registro reg = registroRepositorio.buscarPorEmail(email);
        
        if (reg != null) {
        	
            List<GrantedAuthority> permisos = new ArrayList<>();
                        
            //Creo una lista de permisos! 
            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_"+ reg.getRol());
            permisos.add(p1);
         
            //Esto me permite guardar el OBJETO USUARIO LOGUEADO, para luego ser utilizado
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
	public List<registro> listarRegistrados() {
		return registroRepositorio.findAll();

	}
	
	@Transactional
	public registro findUserByEmail(String email, String password) throws ErrorServicio {
		registro reg = registroRepositorio.buscarPorEmail(email);
		if (reg != null) {
			return validarUsuario(reg, password);
		} else {
			throw new ErrorServicio("No existe el registro.");
		}
	}
	private registro validarUsuario(registro reg, String password) throws ErrorServicio {
		if (reg.getPassword().equals(password)) {
			return reg;
		} else {
			throw new ErrorServicio("Contraseña incorrecta");
		}
	}
	@Transactional
	public registro findById(String id) throws ErrorServicio {
		Optional<registro> respuesta = registroRepositorio.findById(id);
		try {
			respuesta = registroRepositorio.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (respuesta.isPresent()) {
			registro reg = respuesta.get();
			return reg;
		} else {
			throw new ErrorServicio("No se encontro el registro solicitado");
		}
	}
	
	}	
	
	
	












