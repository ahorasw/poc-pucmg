package com.ahorasw.controlevendas.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario", schema="poc")
public class Usuario implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
	@Id
    @Size(max = 80)
    @Column(name = "id", length = 80)
    private String id;

    @NotNull
    @Size(max = 60)
    @Column(name = "email", length = 60)
    private String email;

    @NotNull
    @Size(max = 20)
    @Column(name = "perfil", length = 20)
     private String perfil;
    
    @Size(max = 80)
    @Column(name = "responsavel", length = 80)
    private String responsavel;
    

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}



}
