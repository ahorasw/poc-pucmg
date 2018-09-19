package com.ahorasw.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahorasw.app.model.Pedido;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, String> {

    List<Pedido> findAllByIdParceiro(Integer idParceiro);

    Optional<Pedido> findOneByIdPedido(Integer idPedido);

    Optional<Pedido> findOneByIdOEParceiro(Integer idOrdemEntrega);


}