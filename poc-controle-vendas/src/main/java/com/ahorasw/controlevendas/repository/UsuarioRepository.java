package com.ahorasw.controlevendas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahorasw.controlevendas.domain.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	    Optional<Usuario> findOneById(String id);

	    Optional<Usuario> findOneByEmail(String email);

	    
	}