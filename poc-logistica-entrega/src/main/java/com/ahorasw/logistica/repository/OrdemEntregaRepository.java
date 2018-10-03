package com.ahorasw.logistica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahorasw.logistica.model.OrdemEntrega;


@Repository
public interface OrdemEntregaRepository  extends JpaRepository<OrdemEntrega, Integer> {
	
    OrdemEntrega findOneById(Integer id);
    List<OrdemEntrega> findAllByStatus(Integer status);
    List<OrdemEntrega> findAllByIdfornecedor(Integer idfornecedor);
    List<OrdemEntrega> findAllByIdpedido(Integer idpedido);

}
