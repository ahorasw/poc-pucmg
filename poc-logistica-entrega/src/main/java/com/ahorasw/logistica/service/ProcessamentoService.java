package com.ahorasw.logistica.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ahorasw.logistica.model.ItemOrdemEntrega;
import com.ahorasw.logistica.model.OrdemEntrega;
import com.ahorasw.logistica.model.Pedido;
import com.ahorasw.logistica.repository.OrdemEntregaRepository;
import com.ahorasw.logistica.repository.PedidoRepository;
import com.ahorasw.logistica.service.dto.ItemPedidoFornecedor;
import com.ahorasw.logistica.service.dto.PedidoFornecedor;
import com.ahorasw.logistica.util.Constants;


@Service
public class ProcessamentoService {
	@Autowired
	OrdemEntregaRepository ordemRepository;

	@Autowired
	PedidoRepository pedidoRepository;
	
	
	@Scheduled(fixedDelay = 60000, initialDelay = 60000)
	public void processarNovosPedidos() {
		List<Pedido> novosPedidos = pedidoRepository.findAllByStatus(Constants.PEDIDO_AGUARDANDO_PROCESSAMENTO);

		novosPedidos.forEach(pedido ->{
			//1-Gerar Ordem de venda
			OrdemEntrega ordem = new OrdemEntrega();
			ordem.setIdpedido(pedido.getIdPedido());
			ordem.setStatus(Constants.ORDEM_EM_PREPARACAO);
			ordem.setDestinatario(pedido.getDestinatario());
			ordem.setEndereco(pedido.getEndereco());			
			pedido.getItens().forEach(item -> {
				ItemOrdemEntrega itemOrdem = new ItemOrdemEntrega();
				itemOrdem.setIdProduto(item.getIdProduto());
				itemOrdem.setQuantidade(item.getQuantidade());
				ordem.getProdutos().add(itemOrdem);
			});
			ordemRepository.save(ordem);
			//2-Enviar Fornecedor
			PedidoFornecedor pedidoForn = new PedidoFornecedor();
			pedidoForn.setIdParceiro(Constants.ID_SITE_NO_FORNECEDOR);
			pedidoForn.setIdOEParceiro(ordem.getIdordem());
			ordem.getProdutos().forEach(item -> {
				ItemPedidoFornecedor itemForn = new ItemPedidoFornecedor();
				itemForn.setIdProduto(item.getIdProduto());
				itemForn.setQuantidade(item.getQuantidade());
				pedidoForn.getProdutos().add(itemForn);
			});			
			pedidoForn.setDestinatario(ordem.getDestinatario());
			pedidoForn.setEndereco(ordem.getEndereco());
			
			//3-Atualizar status do Pedido
			pedido.setStatus(Constants.PEDIDO_PROCESSAMENTO_INICIADO);
			pedidoRepository.save(pedido);
			
		});
		
	}
	
	//@Scheduled(fixedDelay = 60000, initialDelay = 60000)
	public void processarSolicitacoesDeDevolucao() {
		System.out.println("TO-DO: Implementar processarSolicitacoesDeDevolucao");
	}

	//@Scheduled(fixedDelay = 60000, initialDelay = 60000)
	public void processarSolicitacoesDeReenvio() {
		System.out.println("TO-DO: Implementar processarSolicitacoesDeReenvio");
		
	}	
	
	@Scheduled(fixedDelay = 60000, initialDelay = 60000)
	public void verificarStatusOrdemEntrega() {
		System.out.println("TO-DO: Implementar verificarStatusOrdemEntrega");
		
	}
	
    public Optional<PedidoFornecedor>  incluirOEFornecedor(PedidoFornecedor pedidoForn, String fornecedorApiUri) {
    	Optional<PedidoFornecedor> pedido = Optional.of(null);   
    	RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
    	HttpEntity<PedidoFornecedor> request = new HttpEntity<>(pedidoForn);
    	ResponseEntity<PedidoFornecedor> response = restTemplate
    	  .exchange(fornecedorApiUri, HttpMethod.POST, request, PedidoFornecedor.class);
    	  
    	
    	if(response.getStatusCode() == HttpStatus.CREATED) {
    		pedido = Optional.of(response.getBody());
    	}	
    	  
    	return pedido;
    	
    }	
    public Optional<PedidoFornecedor> atualizarOEFornecedor(PedidoFornecedor pedidoForn, String fornecedorApiUri) {
    	Optional<PedidoFornecedor> pedido = Optional.of(null);    	
    	RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
    	HttpEntity<PedidoFornecedor> request = new HttpEntity<>(pedidoForn);
    	ResponseEntity<PedidoFornecedor> response = restTemplate
    	  .exchange(fornecedorApiUri, HttpMethod.PUT, request, PedidoFornecedor.class);
    	  
    	if(response.getStatusCode() == HttpStatus.ACCEPTED) {
    		pedido = Optional.of(response.getBody());
    	}	
    	  
    	return pedido;
    	
    }	
    
    public Optional<PedidoFornecedor> consultaOEFornecedor(Integer idPedido, String fornecedorApiUri) {
    	Optional<PedidoFornecedor> pedido = Optional.of(null);
    	
    	RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
    	String fornResourceUrl = fornecedorApiUri+"/ordemEntrega/"+idPedido;
    	ResponseEntity<PedidoFornecedor> response = restTemplate.getForEntity(fornResourceUrl, PedidoFornecedor.class);
 
    	if(response.getStatusCode() == HttpStatus.OK) {
    		pedido = Optional.of(response.getBody());
    	}	
    	  
    	return pedido;
    	
    }	    
    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 5000;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
          = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        return clientHttpRequestFactory;
    }
}
