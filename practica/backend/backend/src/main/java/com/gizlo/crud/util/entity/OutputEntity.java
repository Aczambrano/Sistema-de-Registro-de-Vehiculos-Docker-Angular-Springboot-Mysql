package com.gizlo.crud.util.entity;
import java.util.ArrayList;

import com.gizlo.crud.util.enums.MessageEnum;
import com.gizlo.crud.util.helper.MetodoHelper;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class OutputEntity<T> {

	@JsonIgnore
	private HttpStatus code;
	
	private ArrayList<String> mensaje = new ArrayList<>();
	
	private Boolean error = false;
	
	private T data;
	
	public  OutputEntity<T> ok(Integer code, String mensaje, T data){
		this.code = MetodoHelper.seleccionarEstado(code);
		this.mensaje.add(mensaje);
		this.data = data;
		return this;
	}
	
	public  OutputEntity<T> ok(Integer code, ArrayList<String> mensaje, T data){
		this.code = MetodoHelper.seleccionarEstado(code);
		this.mensaje = mensaje;
		this.data = data;
		return this;
	}

	public OutputEntity<T> error(Integer code, String mensaje, T data) {
		this.code = MetodoHelper.seleccionarEstado(code);
		this.mensaje.add(mensaje);
		this.data = data;
		this.error = true;
		return this;
	}
	
	public OutputEntity<T> error(Integer code, ArrayList<String> mensaje, T data) {
		this.code = MetodoHelper.seleccionarEstado(code);
		this.mensaje = mensaje;
		this.data = data;
		this.error = true;
		return this;
	}
	
	public OutputEntity<T> error() {
		this.code = MetodoHelper.seleccionarEstado(MessageEnum.INTERNAL_ERROR.getCode());
		this.mensaje.add(MessageEnum.INTERNAL_ERROR.getMensaje());
		this.data = null;
		this.error = true;
		return this;
	}
}
