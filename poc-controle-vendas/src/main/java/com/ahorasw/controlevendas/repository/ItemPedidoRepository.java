package com.ahorasw.controlevendas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahorasw.controlevendas.domain.ItemPedido;
import com.ahorasw.controlevendas.domain.Pedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

	List<Pedido> findAllByPedido(Pedido pedido);
    
    Optional<Pedido> findOneById(Integer id);

}
