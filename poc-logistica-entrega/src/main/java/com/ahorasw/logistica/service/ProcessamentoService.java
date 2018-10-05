package com.ahorasw.logistica.service;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.ahorasw.logistica.model.Fornecedor;
import com.ahorasw.logistica.model.ItemOrdemEntrega;
import com.ahorasw.logistica.model.ItemPedido;
import com.ahorasw.logistica.model.OrdemEntrega;
import com.ahorasw.logistica.model.Pedido;
import com.ahorasw.logistica.repository.FornecedorRepository;
import com.ahorasw.logistica.repository.OrdemEntregaRepository;
import com.ahorasw.logistica.repository.PedidoRepository;
import com.ahorasw.logistica.service.dto.ItemPedidoFornecedor;
import com.ahorasw.logistica.service.dto.PedidoFornecedor;
import com.ahorasw.logistica.util.Constants;



@Service
public class ProcessamentoService {
	
    private final Logger log = LoggerFactory.getLogger(ProcessamentoService.class);
	
	@Autowired
	OrdemEntregaRepository ordemRepository;

	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	FornecedorRepository fornecedorRepository;
	
	
	
	@Scheduled(fixedDelay = 6000, initialDelay = 6000)
	public void processarNovosPedidos() {
		System.out.println("Processando Ordens status: NOVOS PEDIDOS");
		List<Pedido> novosPedidos = pedidoRepository.findAllByStatus(Constants.PEDIDO_AGUARDANDO_PROCESSAMENTO);
		
		System.out.println("INICIOU PROCESSAMENTO processarNovosPedidos" + novosPedidos.size());
		novosPedidos.forEach(pedido ->{

			processaPedido(pedido);
			
		});
		
	}
    
	@Scheduled(fixedDelay = 6000, initialDelay = 6000)
	public void processarOrdemEntregaIncluido() {
		System.out.println("Processando Ordens status: ORDEM_PROCESSAMENTO_INCLUIDO");
		List<OrdemEntrega> ordens = ordemRepository.findAllByStatus(Constants.ORDEM_PROCESSAMENTO_INCLUIDO);
		incluirOrdemEntregaFornecedor(ordens);	
	}	

	@Scheduled(fixedDelay = 6000, initialDelay = 6000)
	public void processarStatusOrdemEntregaEmPocessamento() {
		System.out.println("Processando Ordens status: ORDEM_PROCESSAMENTO_INICIADO");
		List<OrdemEntrega> ordens = ordemRepository.findAllByStatus(Constants.ORDEM_PROCESSAMENTO_INICIADO);
		atualizarStatusOrdemEntrega(ordens);	

	}

	@Scheduled(fixedDelay = 6000, initialDelay = 6000)
	public void processarStatusOrdemEntregaEmPreparacao() {
		System.out.println("Processando Ordens status: ORDEM_EM_PREPARACAO_ENVIO");
		List<OrdemEntrega> ordens = ordemRepository.findAllByStatus(Constants.ORDEM_EM_PREPARACAO_ENVIO);
		atualizarStatusOrdemEntrega(ordens);	

	}
	@Scheduled(fixedDelay = 6000, initialDelay = 6000)
	public void processarStatusOrdemEntregaEviados() {
		System.out.println("Processando Ordens status: ORDEM_ENVIADO");
		List<OrdemEntrega> ordens = ordemRepository.findAllByStatus(Constants.ORDEM_ENVIADO);
		atualizarStatusOrdemEntrega(ordens);	

	}
	
	@Scheduled(fixedDelay = 6000, initialDelay = 6000)
	public void processarPedidosEmProcessamento() {
		System.out.println("Processando Pedidos status: PEDIDO_EM_PROCESSAMENTO");
		List<Pedido> pedidos = pedidoRepository.findAllByStatus(Constants.PEDIDO_EM_PROCESSAMENTO);
		
		pedidos.forEach(pedido -> {
			verificaStatusPedidoEmProcessamento(pedido);
		});

	}	
	
	@Transactional
	public void verificaStatusPedidoEmProcessamento(Pedido pedido) {
		boolean pedidoEntregue = true;
		List<OrdemEntrega> ordens = ordemRepository.findAllByIdpedido(pedido.getId());
		
		for(OrdemEntrega ordem : ordens) {			
			if(ordem.getStatus() <= Constants.PEDIDO_EM_PROCESSAMENTO) {
				pedidoEntregue = false; 
				return;
			}	
		}		
		
		if(pedidoEntregue) {
			pedido.setStatus(Constants.PEDIDO_ENTREGUE);
			pedidoRepository.save(pedido);
		}
		
	}

	@Transactional
	public void processaPedido(Pedido pedido){
		
		//Separa os produtos por Fornecedor
		Map<Fornecedor, List<ItemPedido>> mapItemsByFornecedor =
				pedido.getItens().stream().collect(Collectors.groupingBy(w -> w.getPk().getProduto().getFornecedor()));
		
		//Para cada fornecedor é gerado uma Ordem de Entrega
		mapItemsByFornecedor.entrySet().stream().forEach(entry -> {
			//1-Gerar Ordem de venda
			OrdemEntrega ordem = new OrdemEntrega();
			ordem.setIdpedido(pedido.getId());
			ordem.setStatus(Constants.ORDEM_PROCESSAMENTO_INCLUIDO);
			ordem.setDestinatario(pedido.getDestinatario());
			ordem.setEnderecoEntrega(pedido.getEnderecoEntrega());
			ordem.setIdfornecedor(entry.getKey().getId());
			
			entry.getValue().forEach(item -> {
				ItemOrdemEntrega itemOrdem = new ItemOrdemEntrega();
				itemOrdem.getPk().setProduto(item.getPk().getProduto());
				itemOrdem.getPk().setOrdem(ordem);
				itemOrdem.setQuantidade(item.getQuantidade());
				ordem.getItens().add(itemOrdem);
			});
			ordemRepository.save(ordem);
		
		});
		//3-Atualizar status do Pedido
		pedido.setStatus(Constants.PEDIDO_EM_PROCESSAMENTO);
		pedidoRepository.save(pedido);

	}
	
	@Transactional
    public void criarOEFornecedor(OrdemEntrega ordem) {
		//2-Enviar Fornecedor
		PedidoFornecedor pedidoForn = new PedidoFornecedor();
		pedidoForn.setIdParceiro(Constants.ID_SITE_NO_FORNECEDOR);
		pedidoForn.setIdOEParceiro(ordem.getIdordem());
		ordem.getItens().forEach(item -> {
			ItemPedidoFornecedor itemForn = new ItemPedidoFornecedor();
			itemForn.setIdProduto(item.getPk().getProduto().getId());
			itemForn.setQuantidade(item.getQuantidade());
			pedidoForn.getProdutos().add(itemForn);
		});			
		pedidoForn.setDestinatario(ordem.getDestinatario());
		pedidoForn.setEndereco(ordem.getEnderecoEntrega());
		
		String urlFornecedor = fornecedorRepository.findOneById(ordem.getIdfornecedor()).get().getUrlSite();
		
		sendOEFornecedor(pedidoForn, urlFornecedor);
		
		ordem.setStatus(Constants.ORDEM_PROCESSAMENTO_INICIADO);
		ordemRepository.save(ordem);
    	
    }
    
    public Optional<PedidoFornecedor>  sendOEFornecedor(PedidoFornecedor pedidoForn, String fornecedorApiUri) {
    	Optional<PedidoFornecedor> pedido = null;   
    	RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
    	HttpEntity<PedidoFornecedor> request = new HttpEntity<>(pedidoForn);
    	ResponseEntity<PedidoFornecedor> response = restTemplate
    	  .exchange(fornecedorApiUri+"/ordemEntrega", HttpMethod.POST, request, PedidoFornecedor.class);
    	  
    	
    	if(response.getStatusCode() == HttpStatus.CREATED) {
    		pedido = Optional.of(response.getBody());
    	}else
    		throw new RuntimeException("Ordem de Servico nao incluida no Fornecedor!!");
    	  
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
	
	public void incluirOrdemEntregaFornecedor(List<OrdemEntrega> ordens) {

		ordens.forEach(ordem ->{
			try {
				criarOEFornecedor(ordem);
			
			}catch(Exception e) {log.debug("Erro na inclusao da Ordem de Entrega no fornecedor: "+ e.getMessage());}	
		});
		
	}	
	
	public void atualizarStatusOrdemEntrega(List<OrdemEntrega> ordens) {

		ordens.forEach(ordem ->{
			try {
				atualizarStatusOrdemEntrega(ordem);
			}catch(Exception e) {log.debug("Erro atualiza Pedido: "+ e.getMessage());}	
		});
	}	
	
	@Transactional
	public void atualizarStatusOrdemEntrega(OrdemEntrega ordem) {
		Optional<Fornecedor> forn = fornecedorRepository.findOneById(ordem.getIdfornecedor());
		
		PedidoFornecedor pedido = getStatusOrdemEntrega(ordem.getIdordem(), forn.get().getUrlSite() );
		
		if(pedido.getStatus() == Constants.FORN_PEDIDO_ENVIADO)
			ordem.setCodrastreamento(pedido.getCodRastreamento());
		
		ordem.setStatus(pedido.getStatus());
		ordemRepository.save(ordem);
	}	
	
	
	public PedidoFornecedor getStatusOrdemEntrega(Integer idOrdem, String urlSite) {
		
    	String urlStatus = String.format("%s/ordemEntrega/%d", urlSite, idOrdem);  
		
    	RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
    	ResponseEntity<PedidoFornecedor> response = restTemplate
    	  .exchange(urlStatus , HttpMethod.GET, null, PedidoFornecedor.class);
    	  
    	if(response.getStatusCode() != HttpStatus.OK) 
    		throw new RuntimeException("Erro na consulta do Status da Ordem de Servico no site do Fornecedor!!");
    
    	return response.getBody();     	  
    	
	}
	
    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 5000;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
          = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        return clientHttpRequestFactory;
    }	
	//@Scheduled(fixedDelay = 60000, initialDelay = 60000)
	public void processarSolicitacoesDeDevolucao() {
		System.out.println("TO-DO: Implementar processarSolicitacoesDeDevolucao");
	}

	//@Scheduled(fixedDelay = 60000, initialDelay = 60000)
	public void processarSolicitacoesDeReenvio() {
		System.out.println("TO-DO: Implementar processarSolicitacoesDeReenvio");
		
	}	
}
