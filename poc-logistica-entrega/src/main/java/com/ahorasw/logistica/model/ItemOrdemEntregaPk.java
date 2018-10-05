package com.ahorasw.logistica.model;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ItemOrdemEntregaPk  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private OrdemEntrega ordem;
    private Produto produto;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idordem", nullable = false)
	public OrdemEntrega getOrdem() {
		return ordem;
	}

	public void setOrdem(OrdemEntrega ordem) {
		this.ordem = ordem;
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
