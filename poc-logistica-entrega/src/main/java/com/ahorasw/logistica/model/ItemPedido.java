package com.ahorasw.logistica.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "item_pedido", schema="poc")
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer    idProduto;
    private Integer    quantidade;

    public ItemPedido() {
		super();
    }
    

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
