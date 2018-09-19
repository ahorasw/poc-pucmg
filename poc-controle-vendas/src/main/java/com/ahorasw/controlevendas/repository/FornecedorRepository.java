package com.ahorasw.controlevendas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahorasw.controlevendas.domain.Fornecedor;


@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, String> {

    Optional<Fornecedor> findOneById(Integer id);

    Optional<Fornecedor> findOneByNome(String nome);

    Optional<Fornecedor> findOneByCnpj(Integer cnpj);
    
}