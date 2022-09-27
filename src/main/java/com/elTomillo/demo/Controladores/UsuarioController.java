package com.elTomillo.demo.Controladores;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.errores.ErrorServicio;
import com.elTomillo.demo.DTOs.UsuarioDTO;
import com.elTomillo.demo.Entidades.*;
import com.elTomillo.demo.Exceptions.ArgumentNotValidException;
import com.elTomillo.demo.Repositorios.UsuarioRepositorio;
import com.elTomillo.demo.Servicios.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@PostMapping("/registro")
	public ResponseEntity<UsuarioDTO> save(@Valid @RequestBody UsuarioDTO usuarioDTO) throws Exception {
		return ResponseEntity.ok().body(usuarioService.registrarUsuario(usuarioDTO));
	}

	@PutMapping("/modificarsuario/{id}")
	public String modificarUsuario(@PathVariable long id, ModelMap modelo) throws ErrorServicio {
		return "";
	}

}
