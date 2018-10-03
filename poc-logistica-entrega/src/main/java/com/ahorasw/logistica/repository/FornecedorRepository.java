package com.ahorasw.logistica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahorasw.logistica.model.Fornecedor;

public interface FornecedorRepository  extends JpaRepository<Fornecedor, Integer> {

    Optional<Fornecedor> findOneById(Integer id);

}
