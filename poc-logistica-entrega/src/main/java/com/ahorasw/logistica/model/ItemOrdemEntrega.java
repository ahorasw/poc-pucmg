package com.ahorasw.logistica.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "item_ordem_entrega", schema="poc")
public class ItemOrdemEntrega {

	private ItemOrdemEntregaPk pk = new ItemOrdemEntregaPk(); 

    private Integer    quantidade;


	@EmbeddedId
	public ItemOrdemEntregaPk getPk() {
		return pk;
	}

	public void setPk(ItemOrdemEntregaPk pk) {
		this.pk = pk;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}


}
