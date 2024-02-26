package com.gizlo.crud.util.enums;
import lombok.Getter;

@Getter
public enum MessageEnum {

	OK("Respuesta exitosa.", 200),
	CREATE("Creación exitosa", 201),
	INTERNAL_ERROR("Problema en la transacción", 500),
	NOT_FOUND("No se encontraron resultados", 404),
	LOGIN_ERROR("Usuario o clave incorrecto", 404),
	CORREO_NO_VALIDO("Ingrese un correo válido.", 403),
	UPPER_CASE("Ingrese el texto en mayúscula.", 403),
	NOT_STRONGE_PASS("Su contraseña no es segura", 403),
	NOT_ENCRYPT("Hubo un conflicto", 409);
	
	private String mensaje;
	private Integer code;
	
	private MessageEnum(String mensaje, Integer code) {
		this.mensaje = mensaje;
		this.code = code;
	}
}
