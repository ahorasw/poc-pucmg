package com.ahorasw.controlevendas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahorasw.controlevendas.domain.Avaliacao;

public interface AvaliacaoProdutoRepository extends JpaRepository<Avaliacao, Integer> {

	List<Avaliacao> findAllByProduto(Integer id);
    
}