package com.ahorasw.controlevendas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahorasw.controlevendas.domain.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, String> {

	List<Produto> findAllByCategoria(String Categoria);

    Optional<Produto> findOneById(Integer id);

    Optional<Produto> findOneByNome(String nome);

}