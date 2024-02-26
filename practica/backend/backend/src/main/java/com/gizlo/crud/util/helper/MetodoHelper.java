package com.gizlo.crud.util.helper;

import org.springframework.http.HttpStatus;

public class MetodoHelper {

	public static HttpStatus seleccionarEstado(Integer code) {
		HttpStatus status = null;

		switch (code) {
		case 200:
			status = HttpStatus.OK;
			break;
		case 201:
			status = HttpStatus.CREATED;
			break;
		case 400:
			status = HttpStatus.BAD_REQUEST;
			break;
		case 404:
			status = HttpStatus.NOT_FOUND;
			break;
		case 406:
			status = HttpStatus.NOT_ACCEPTABLE;
			break;
		case 204:
			status = HttpStatus.NO_CONTENT;
			break;
		case 401:
			status = HttpStatus.UNAUTHORIZED;
			break;
		case 409:
			status = HttpStatus.CONFLICT;
			break;
		case 403:
			status = HttpStatus.FORBIDDEN;
			break;
		case 405:
			status = HttpStatus.METHOD_NOT_ALLOWED;
			break;
		case 408:
			status = HttpStatus.REQUEST_TIMEOUT;
			break;
		case 500:
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			break;
		}

		return status;
	}



}
