package com.ahorasw.logistica.service.dto;

import java.util.ArrayList;
import java.util.List;

public class PedidoFornecedor{

    private Integer    idParceiro; //Nosso site 
    private Integer    idOEParceiro; //Id da Ordem de entrega do parceiro
    private String     codRastreamento;
    private Integer    status;
    private String destinatario;
    private String endereco;
    
    private List<ItemPedidoFornecedor> produtos = new ArrayList<>();

	public Integer getIdParceiro() {
		return idParceiro;
	}
	public void setIdParceiro(Integer idParceiro) {
		this.idParceiro = idParceiro;
	}
	public Integer getIdOEParceiro() {
		return idOEParceiro;
	}
	public void setIdOEParceiro(Integer idOEParceiro) {
		this.idOEParceiro = idOEParceiro;
	}
	public String getCodRastreamento() {
		return codRastreamento;
	}
	public void setCodRastreamento(String codRastreamento) {
		this.codRastreamento = codRastreamento;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<ItemPedidoFornecedor> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<ItemPedidoFornecedor> produtos) {
		this.produtos = produtos;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
