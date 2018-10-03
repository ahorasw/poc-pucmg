 package com.ahorasw.logistica.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "produto", schema="poc")
public class Produto implements Serializable {
	
    private static final long serialVersionUID = 2L;
    
	@Id
	private Integer id;

    private String nome;

    private String categoria;
    
    private String descricao;

    private String detalhe;
    
    private BigDecimal preco;
    
    @ManyToOne
    @JoinColumn(name="idfornecedor", nullable=false)
    private Fornecedor fornecedor;

    
	public Produto() {
		
	}	


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getDetalhe() {
		return detalhe;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}


	public BigDecimal getPreco() {
		return preco;
	}


	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}


	public Fornecedor getFornecedor() {
		return fornecedor;
	}


	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
