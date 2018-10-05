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
@Table(name = "ordem_entrega", schema="poc")
@SequenceGenerator(name="ordem_entrega_seq", sequenceName="poc.ordem_entrega_sequence", allocationSize = 1, initialValue = 1  ) 
public class OrdemEntrega implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "ordem_entrega_seq")
    private Integer    id;
    private Integer    idfornecedor;
    private Integer    idpedido;
    private String     codrastreamento;
    private String     destinatario;
    private String     enderecoEntrega;    
    private Integer    status;

   
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval=true,cascade=CascadeType.ALL)
    @JoinColumn(name = "idordem", nullable = false, insertable=false, updatable=false)
    private List<ItemOrdemEntrega> itens = new ArrayList<>();
    
    public OrdemEntrega() {
		super();
    }
    
    public void setIdfornecedor( Integer idfornecedor ) {
        this.idfornecedor = idfornecedor ;
    }
    public Integer getIdfornecedor() {
        return this.idfornecedor;
    }

    public void setIdpedido( Integer idpedido ) {
        this.idpedido = idpedido ;
    }
    public Integer getIdpedido() {
        return this.idpedido;
    }

    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getIdordem() {
        return this.id;
    }

    public void setCodrastreamento( String codrastreamento ) {
        this.codrastreamento = codrastreamento ;
    }
    public String getCodrastreamento() {
        return this.codrastreamento;
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}


	public String getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(String enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}


	public List<ItemOrdemEntrega> getItens() {
		return itens;
	}

	public void setItens(List<ItemOrdemEntrega> itens) {
		this.itens = itens;
	}




}
