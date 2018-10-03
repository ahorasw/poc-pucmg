package com.ahorasw.logistica.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
@SequenceGenerator(name="pedido_seq", sequenceName="poc.pedido_sequence", allocationSize = 1, initialValue = 1 ) 
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
	@Id
	@GeneratedValue(generator = "pedido_seq")
    private Integer    id;
	
    private String    idusuario;
    private Integer    status;
    private String     enderecoEntrega;
    private String     destinatario;
    private Date       dataPedido;
    private Double     valorTotal;

    @OneToMany(fetch = FetchType.EAGER,orphanRemoval=true,cascade=CascadeType.ALL)
    @JoinColumn(name = "idpedido", nullable = false, insertable=false, updatable=false)
    private List<ItemPedido> itens = new ArrayList();

    public Pedido() {
		super();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
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

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
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

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	


}
