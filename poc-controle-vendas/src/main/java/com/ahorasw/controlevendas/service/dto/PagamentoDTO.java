package com.ahorasw.controlevendas.service.dto;

import java.io.Serializable;

public class PagamentoDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3734498082532994694L;
	private String idPagamento;
	private String UrlRedirect;
	public String getIdPagamento() {
		return idPagamento;
	}
	public void setIdPagamento(String idPagamento) {
		this.idPagamento = idPagamento;
	}
	public String getUrlRedirect() {
		return UrlRedirect;
	}
	public void setUrlRedirect(String urlRedirect) {
		UrlRedirect = urlRedirect;
	}
	
	
	
}
