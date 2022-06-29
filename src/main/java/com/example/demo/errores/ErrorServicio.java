package com.example.demo.errores;

//package com.Inmobiliaria2.0.app.errores;

public class ErrorServicio extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorServicio(String mensaje) {
		super(mensaje);
	}

}
