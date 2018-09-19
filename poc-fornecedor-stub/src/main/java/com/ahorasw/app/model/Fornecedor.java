package com.ahorasw.app.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "fornecedor", schema="poc")
@SequenceGenerator(name="fornecedor_seq", sequenceName="poc.fornecedor_generator" ) 
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fornecedor_seq")
    private Integer    idfornecedor;
    private String     nome;
    private Integer    cnpj;
    private String     urlsite;
    private Integer    versaoapi;

    public Fornecedor() {
		super();
    }
    

	public Integer getIdfornecedor() {
		return idfornecedor;
	}

	public void setIdfornecedor(Integer idfornecedor) {
		this.idfornecedor = idfornecedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCnpj() {
		return cnpj;
	}

	public void setCnpj(Integer cnpj) {
		this.cnpj = cnpj;
	}

	public String getUrlsite() {
		return urlsite;
	}

	public void setUrlsite(String urlsite) {
		this.urlsite = urlsite;
	}

	public Integer getVersaoapi() {
		return versaoapi;
	}

	public void setVersaoapi(Integer versaoapi) {
		this.versaoapi = versaoapi;
	}



}
