package com.ahorasw.controlevendas.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "arquivosmidia", schema="poc")
@SequenceGenerator(name="arquivos_midia_seq", sequenceName="poc.arquivos_midia_sequence", allocationSize = 1, initialValue = 1 ) 
public class ArquivoMedia implements Serializable {
	
	private static final Integer MEDIA_TYPE_UNDEFINED = 0;
	private static final Integer MEDIA_TYPE_IMG_LIST = 1;
	private static final Integer MEDIA_TYPE_IMG_DET_M = 2;
	private static final Integer MEDIA_TYPE_IMG_DET_G = 3;
	private static final Integer MEDIA_TYPE_IMG_VIDEO = 4;

    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "arquivos_midia_seq")
    private Integer id;
    private Integer tipo;
    private String  uri;
    private Integer sequencia;
    
    public ArquivoMedia() {
	}
	


	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		tipo = (tipo<1 || tipo>4)?MEDIA_TYPE_UNDEFINED:tipo;//Se tipo diferente dos definidos = 0
		this.tipo = tipo;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}


	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}
	

}
