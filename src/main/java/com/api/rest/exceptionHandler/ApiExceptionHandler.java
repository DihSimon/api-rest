package com.api.rest.exceptionHandler;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Classe para capturar exceção de response entity
 * @ControllerAdvice
 * Conttroler para observar toda a aplicação, afim de encontrar mensagens de erros
 * @author Diego
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	
	@Autowired
	private MessageSource mensagemSource;
	

	/**
	 * Metoto para tratar exceção, não é possivel ler a mensagem
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		String mensagemUsuario = mensagemSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	/**
	 * Metodo para validar argumentos não validos
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Erro> erros = criarListaErros(ex.getBindingResult());
		return super.handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> emptyResultDataAccessException(EmptyResultDataAccessException ex,
			 WebRequest request) {
		String mensagemUsuario = mensagemSource.getMessage("recurso.nao.encontrado", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request); 
	}
	
	/**
	 * Metodo para capturar os erros na lista
	 * @return
	 */
	private List<Erro> criarListaErros(BindingResult bindingResult){
		List<Erro> erros = new ArrayList<>();
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			String mensagemUsuario = mensagemSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor = fieldError.toString();
			erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		}
		return erros;
	}

	/**
	 * Classe que contem os atributos de erros
	 * @author resource189
	 *
	 */
	public static class Erro {
		
		public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
			super();
			this.setMensagemUsuario(mensagemUsuario);
			this.setMensagemDesenvolvedor(mensagemDesenvolvedor);
		}
		
		private String mensagemUsuario;
		private String mensagemDesenvolvedor;
		
		
		public String getMensagemUsuario() {
			return mensagemUsuario;
		}
		public void setMensagemUsuario(String mensagemUsuario) {
			this.mensagemUsuario = mensagemUsuario;
		}


		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}


		public void setMensagemDesenvolvedor(String mensagemDesenvolvedor) {
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}
	}
}
