package com.ahorasw.controlevendas.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "produto", schema="poc")
@SequenceGenerator(name="produto_seq", sequenceName="poc.produto_sequence", allocationSize = 1, initialValue = 1 ) 
public class Produto implements Serializable {
	
    private static final long serialVersionUID = 2L;
    
	@Id
	@GeneratedValue(generator = "produto_seq")
    @Column(name = "id", updatable = false, nullable = false)
	private Integer id;

    @NotNull
    @Size(max = 100)
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Size(max = 100)
    @Column(name = "categoria")
    private String categoria;
    
    @Column(name = "descricao")
    private String descricao;

    @Column(name = "detalhe")
    private String detalhe;
    
    @NotNull
    @Column(name = "preco")
    private Double valor;
   
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idfornecedor", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Fornecedor fornecedor;
    
    
    @OneToMany(fetch = FetchType.EAGER,orphanRemoval=true,cascade=CascadeType.ALL)
    @JoinColumn(name = "idproduto", nullable = false)
    private List<ArquivoMedia> medias = new ArrayList<>();

    
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public List<ArquivoMedia> getMedias() {
		return medias;
	}
	public void setMedias(List<ArquivoMedia> medias) {
		this.medias = medias;
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


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}



}
