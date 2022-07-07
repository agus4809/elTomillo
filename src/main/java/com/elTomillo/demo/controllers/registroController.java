package com.elTomillo.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elTomillo.demo.servicios.registroService;
import com.example.demo.errores.ErrorServicio;
import com.elTomillo.demo.entidades.*;
import com.elTomillo.demo.repositorios.registroRepositorio;




@Controller
@RequestMapping("/registroUsuario")
public class registroController {

	@Autowired 
	private registroService regServ;
	@Autowired
	private registroRepositorio regRepo;

		@GetMapping("/form")
		public String registro() {
			return "/registroUsuario";
		}

		@PostMapping("/form")
		public String crearRegistro(ModelMap modelo, @RequestParam String nombre, @RequestParam String lastName, 
				@RequestParam String bussinesName, @RequestParam String email, @RequestParam String password) {
			
			try {
				regServ.crearRegistro(nombre, lastName, bussinesName, email, password);
				
				modelo.put("exito", "el usuario se ha creado exitosamente");
				return "/registroUsuario";
				
			} catch (Exception e) {
				modelo.put("error", "no se ha podido registrar el usuario");
				return "/registroUsuario";
			}
			
		}

		@GetMapping("/modificarsuario/{id}")
		public String modificarUsuario(@PathVariable String id, ModelMap modelo) throws ErrorServicio {
			
			
			modelo.put("usuario", regServ.findById(id));
			
			return "/modificarUsuario";
		}
		
		@PostMapping("/modificarUsuario")
		public String modificarUsuario (ModelMap modelo, String id, @RequestParam String nombre, @RequestParam String lastName, 
				@RequestParam String bussinesName, @RequestParam String email, @RequestParam String password) {
			
			try {
				regServ.modificarUsuario(id, bussinesName, lastName, bussinesName, email, password);
				
				return "/listadoUsuario";
				
			} catch (Exception e) {
				return "/modificarUsuario";
			}
		}
		
		@GetMapping("/listadoUsuarios")
		public String listado (ModelMap modelo) {
			
			
			List <Registro> xx = regServ.listarRegistrados();
			modelo.addAttribute("usuario", xx);
			
			return "/listadoUsuarios";
		}
		
		
		
		
		
		
		

}
