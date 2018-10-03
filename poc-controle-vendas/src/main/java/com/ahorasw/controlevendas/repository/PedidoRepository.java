package com.ahorasw.controlevendas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahorasw.controlevendas.model.Pedido;
import com.ahorasw.controlevendas.model.Usuario;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	List<Pedido> findAllByStatus(Integer status);
	
	List<Pedido> findAllByUsuario(Usuario usuario);
    
    Optional<Pedido> findOneById(Integer id);
  
}