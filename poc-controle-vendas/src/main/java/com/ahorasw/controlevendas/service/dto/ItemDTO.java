package com.ahorasw.controlevendas.service.dto;

import java.io.Serializable;

public class ItemDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3734498082532994693L;
	
	private Integer id;
	private String  descricao;
	private Integer quantidade;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
		
}