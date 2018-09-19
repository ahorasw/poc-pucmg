package com.ahorasw.logistica.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "pedido", schema="poc")
@SequenceGenerator(name="pedido_seq", sequenceName="poc.pedido_generator" ) 
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_seq")
    private Integer    idPedido;
    private String     idUsuario;
    private String     destinatario;
    private String     endereco;    
    private Integer    status;
    private String     codPagamento;    

    @OneToMany(fetch = FetchType.EAGER,orphanRemoval=true,cascade=CascadeType.ALL)
    @JoinColumn(name = "idPedido", nullable = false)
    private List<ItemPedido> itens = new ArrayList();

    public Pedido() {
		super();
    }

	
	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public List<ItemPedido> getItens() {
		return itens;
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


	public String getCodPagamento() {
		return codPagamento;
	}


	public void setCodPagamento(String codPagamento) {
		this.codPagamento = codPagamento;
	}


	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}


}
