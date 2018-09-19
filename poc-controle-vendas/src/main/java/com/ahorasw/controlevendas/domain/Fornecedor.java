package com.ahorasw.controlevendas.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "fornecedor", schema="poc")
@SequenceGenerator(name="fornecedor_seq", sequenceName="poc.fornecedor_sequence", allocationSize = 1, initialValue = 1 ) 
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "fornecedor_seq")
    private Integer    id;
    private String     nome;
    private Long       cnpj;
    private String     urlSite;
    private Integer    versaoApi;

    public Fornecedor() {
		super();
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

	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}


	public String getUrlSite() {
		return urlSite;
	}


	public void setUrlSite(String urlSite) {
		this.urlSite = urlSite;
	}


	public Integer getVersaoApi() {
		return versaoApi;
	}


	public void setVersaoApi(Integer versaoApi) {
		this.versaoApi = versaoApi;
	}


}
