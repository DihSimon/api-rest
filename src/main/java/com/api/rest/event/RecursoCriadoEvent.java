package com.api.rest.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

/**
 * Classe para trabalhar com eventos
 * @author Diego
 * @version 1.0
 */
public class RecursoCriadoEvent extends ApplicationEvent{

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse response;
	
	private Long codigo;

	public RecursoCriadoEvent(Object source, HttpServletResponse response, Long codigo) {
		super(source);
		this.response = response;
		this.codigo = codigo;
		
	}

	public Long getCodigo() {
		return codigo;
	}

	public HttpServletResponse getResponse() {
		return response;
	}


}
