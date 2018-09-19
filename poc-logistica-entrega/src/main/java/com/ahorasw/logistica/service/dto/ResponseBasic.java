package com.ahorasw.logistica.service.dto;

public class ResponseBasic<T> {
	
	private int    codRetorno;
	private String mensagem;
	private T dto;
	public int getCodRetorno() {
		return codRetorno;
	}
	public void setCodRetorno(int codRetorno) {
		this.codRetorno = codRetorno;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public T getDto() {
		return dto;
	}
	public void setDto(T dto) {
		this.dto = dto;
	}
	public ResponseBasic(int codRetorno, String mensagem, T dto) {
		super();
		this.codRetorno = codRetorno;
		this.mensagem = mensagem;
		this.dto = dto;
	}	

}
