package com.ahorasw.controlevendas.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "item_pedido", schema="poc")
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

	private ItemPedidoPk pk = new ItemPedidoPk(); 
    private Integer  quantidade;
    
    
    
    public ItemPedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	@EmbeddedId
	public ItemPedidoPk getPk() {
		return pk;
	}

	public void setPk(ItemPedidoPk pk) {
		this.pk = pk;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	

}
