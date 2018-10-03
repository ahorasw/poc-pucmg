package com.ahorasw.logistica.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ItemPedidoPk  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pedido pedido;
    private Produto produto;
    
    
    public ItemPedidoPk() {
	}

    public ItemPedidoPk(Pedido pedido, Produto produto) {
    	this.pedido = pedido;
    	this.produto = produto;
    }
    
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idpedido", nullable=false)
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idproduto", nullable = false)
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}   		
		    
}
