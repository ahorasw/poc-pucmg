package com.ahorasw.logistica.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahorasw.logistica.model.Pedido;
import com.ahorasw.logistica.repository.OrdemEntregaRepository;
import com.ahorasw.logistica.repository.PedidoRepository;

@Service
public class LogisticaService {
	
	@Autowired
	OrdemEntregaRepository oeRepository;

	@Autowired
	PedidoRepository pedidoRepository;	
	
	public Integer getStatusPedido(Integer idPedido) {
		
		Optional<Pedido> pedido = pedidoRepository.findOneById(idPedido);
		
		if(!pedido.isPresent()) {
			throw new RuntimeException("Pedido nao Encontredo!");
		}

		return pedido.get().getStatus();

	}
}
