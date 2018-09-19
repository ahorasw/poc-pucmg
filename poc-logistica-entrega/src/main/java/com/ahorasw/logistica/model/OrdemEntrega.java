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
@SequenceGenerator(name="ordem_entrega_seq", sequenceName="poc.ordem_entrega_generator" ) 
public class OrdemEntrega implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer    idordem;
    private Integer    idfornecedor;
    private Integer    idpedido;
    private String     codrastreamento;
    private String     destinatario;
    private String     endereco;    
    private Integer    status;
    private String     codPagamento;  
   
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval=true,cascade=CascadeType.ALL)
    @JoinColumn(name = "idordem", nullable = false)
    private List<ItemOrdemEntrega> produtos = new ArrayList<>();
    
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

    public void setIdordem( Integer idordem ) {
        this.idordem = idordem ;
    }
    public Integer getIdordem() {
        return this.idordem;
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

	public List<ItemOrdemEntrega> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ItemOrdemEntrega> produtos) {
		this.produtos = produtos;
	}


}
