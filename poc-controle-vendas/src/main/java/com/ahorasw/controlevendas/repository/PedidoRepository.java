package com.ahorasw.controlevendas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahorasw.controlevendas.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	List<Pedido> findAllByStatus(Integer status);
    
    Optional<Pedido> findOneById(Integer id);
  
}