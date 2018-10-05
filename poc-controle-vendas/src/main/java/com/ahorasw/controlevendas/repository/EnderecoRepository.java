package com.ahorasw.controlevendas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahorasw.controlevendas.model.Endereco;
import com.ahorasw.controlevendas.model.Usuario;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

	    List<Endereco> findAllByUsuario(Usuario user);
	    Optional<Endereco> findOneById(Integer id);
}