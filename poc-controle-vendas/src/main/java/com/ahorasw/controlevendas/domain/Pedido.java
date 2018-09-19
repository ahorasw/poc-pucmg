package com.ahorasw.controlevendas.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "pedido", schema="poc")
@SequenceGenerator(name="pedido_seq", sequenceName="poc.pedido_sequence", allocationSize = 1, initialValue = 1 ) 
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
	@Id
	@GeneratedValue(generator = "pedido_seq")
    private Integer    id;
	
    private Integer    status;
    private String     enderecoEntrega;
    private String     destinario;
    private Date       dataPedido;
    private Double     valorTotal;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idusuario", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Usuario usuario;   
	
    public Pedido() {
		super();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getEnderecoEntrega() {
		return enderecoEntrega;
	}


	public void setEnderecoEntrega(String enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}


	public String getDestinario() {
		return destinario;
	}


	public void setDestinario(String destinario) {
		this.destinario = destinario;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Date getDataPedido() {
		return dataPedido;
	}


	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	
}
