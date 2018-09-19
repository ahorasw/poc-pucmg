package com.ahorasw.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
	@Id
	@GeneratedValue
	@JsonIgnore
    private Integer    idPedido;
    private Integer    idParceiro; //Nosso site 
    private Integer    idOEParceiro; //Id da Ordem de entrega do parceiro
    private String     codRastreamento;
    private Integer    status;
    
    @OneToMany(fetch = FetchType.EAGER,orphanRemoval=true,cascade=CascadeType.ALL)
    @JoinColumn(name = "idPedido", nullable = false)
    private List<ItemPedido> produtos = new ArrayList<>();

    private String destinatario;
    private String endereco;
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
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
	public List<ItemPedido> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<ItemPedido> produtos) {
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
