package com.ahorasw.app.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ahorasw.app.model.Pedido;
import com.ahorasw.app.repository.PedidoRepository;
import com.ahorasw.app.util.Constants;



@Service
public class FornecedorService {
	
		
	@Autowired
	PedidoRepository repository;
	
	public Pedido incluirOrdemEntrega(Pedido ordemEntrega) throws Exception{
		Optional<Pedido> pedido = repository.findOneByIdOEParceiro(ordemEntrega.getIdOEParceiro());
		
		
		if(pedido.isPresent()) {
			throw new Exception("Ordem de Entrega já Cadastrada!");
		}
		

		ordemEntrega.setStatus(Constants.FORN_PEDIDO_ACATADO);
		repository.save(ordemEntrega);
		
		return ordemEntrega;
	}

	public Pedido consultaPedido(Integer idOrdemEntrega) throws Exception{
		Optional<Pedido> pedido = repository.findOneByIdOEParceiro(idOrdemEntrega);
		
		if(!pedido.isPresent()) {
			throw new Exception("Ordem de Entrega nao Cadastrada!");
		}

		return pedido.get();
	}
	
	public List<Pedido> consultaAllPedido() throws Exception{
		List<Pedido> pedidos = repository.findAll();
		
		if(pedidos == null || pedidos.size()==0) {
			throw new Exception("Ordem de Entrega nao Cadastrada!");
		}

		return pedidos;
	}	
	
	public Pedido atualizarOrdemEntrega(Pedido ordemEntrega) throws Exception{
		Optional<Pedido> pedido = repository.findOneByIdOEParceiro(ordemEntrega.getIdOEParceiro());
		
		
		if(!pedido.isPresent()) {
			throw new Exception("Ordem de Entrega nao Cadastrada!");
		}
		
		Pedido novoPedido = new Pedido();
		novoPedido.setIdPedido(pedido.get().getIdPedido());
		novoPedido.setIdParceiro(ordemEntrega.getIdParceiro());
		novoPedido.setIdOEParceiro(ordemEntrega.getIdOEParceiro());
		novoPedido.setEndereco(ordemEntrega.getEndereco());
		novoPedido.setDestinatario(ordemEntrega.getDestinatario());
		novoPedido.setStatus(Constants.FORN_PEDIDO_ACATADO);

		ordemEntrega.getProdutos().forEach(item-> novoPedido.getProdutos().add(item));
		repository.save(novoPedido);
		
		return novoPedido;
	}

	public void excluirPedido(Integer idOrdemEntrega) throws Exception{
		Optional<Pedido> pedido = repository.findOneByIdOEParceiro(idOrdemEntrega);
		
		if(!pedido.isPresent()) {
			throw new Exception("Ordem de Entrega nao Cadastrada!");
		}

		repository.delete(pedido.get());
	}
	
	public void atualizaStatusPedido(Integer idOrdemEntrega, Integer status) throws Exception{
		Optional<Pedido> pedido = repository.findOneByIdOEParceiro(idOrdemEntrega);
		
		
		if(!pedido.isPresent()) {
			throw new Exception("Ordem de Entrega nao Cadastrada!");
		}	

		if(pedido.get().getStatus() > status)
			throw new Exception("Status da Entrega nao pode ser alterada!");
			
		pedido.get().setStatus(status);
		
		repository.save(pedido.get());
	}
	
	
	@Scheduled(fixedDelay = 60000, initialDelay = 60000)
	public void atualizarPedidos(){
		System.out.println("Executando metodo: atualizarPedidos!");		
		List<Pedido> pedidos = 	repository.findAll();
		
		if(pedidos==null || pedidos.size()==0) {
			System.out.println("Nenhum Pedido Encontrado!");
			return;
		}
			
		pedidos.forEach(pedido -> {
			if(pedido.getStatus()==Constants.FORN_PEDIDO_EM_PREPARACAO_ENVIO) {
				pedido.setCodRastreamento("BR"+RandomStringUtils.randomAlphanumeric(12).toUpperCase());
			}
			if(pedido.getStatus() < Constants.FORN_PEDIDO_ENTREGUE) {
				pedido.setStatus(pedido.getStatus()+1);
				repository.save(pedido);
			}
		});

	}

}
