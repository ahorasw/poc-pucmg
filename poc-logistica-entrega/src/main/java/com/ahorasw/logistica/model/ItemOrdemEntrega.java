package com.ahorasw.logistica.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "item_ordem_entrega", schema="poc")
public class ItemOrdemEntrega {

    private Integer    idProduto;
    private Integer    quantidade;


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
