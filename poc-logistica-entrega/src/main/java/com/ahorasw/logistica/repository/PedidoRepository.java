package com.ahorasw.logistica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahorasw.logistica.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findAllById(Integer status);
    List<Pedido> findAllByStatus(Integer status);
    List<Pedido> findAllByIdpedido(Integer idpedido);

}
