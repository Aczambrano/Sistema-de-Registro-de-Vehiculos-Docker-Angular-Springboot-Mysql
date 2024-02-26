package com.gizlo.crud.util.exception;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class MyException extends Exception{

	private static final long serialVersionUID = 1L;
	private ArrayList<String> mensaje = new ArrayList<>();
	private Integer code;
	private HttpStatus codeHttp;
	

	public MyException(Integer code, String mensaje) {
		super();
		this.code = code;
		this.mensaje.add(mensaje);
	}
	
}
