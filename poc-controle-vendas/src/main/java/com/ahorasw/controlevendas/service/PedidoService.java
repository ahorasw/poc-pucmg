package com.ahorasw.controlevendas.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.ahorasw.controlevendas.domain.Endereco;
import com.ahorasw.controlevendas.domain.ItemPedido;
import com.ahorasw.controlevendas.domain.Pedido;
import com.ahorasw.controlevendas.domain.Produto;
import com.ahorasw.controlevendas.domain.Usuario;
import com.ahorasw.controlevendas.repository.EnderecoRepository;
import com.ahorasw.controlevendas.repository.ItemPedidoRepository;
import com.ahorasw.controlevendas.repository.PedidoRepository;
import com.ahorasw.controlevendas.repository.ProdutoRepository;
import com.ahorasw.controlevendas.repository.UsuarioRepository;
import com.ahorasw.controlevendas.service.dto.ItemDTO;
import com.ahorasw.controlevendas.service.dto.PedidoDTO;
import com.ahorasw.controlevendas.util.Util;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;
    
    @Autowired
    private ProdutoRepository produtoReporitory;
    
    @Autowired
    private EnderecoRepository enderecoReporitory;

    @Autowired
    private UsuarioRepository usuarioReporitory;
    
    @Autowired
    private ItemPedidoRepository itemPedidoReporitory;
        
    public PedidoRepository getRepository() {
		return repository;
	}

	public void setRepository(PedidoRepository repository) {
		this.repository = repository;
	}

	@Autowired
    private DiscoveryClient discoveryClient;
    
    public String getStatusPedido(String param) {
    	
    	List<ServiceInstance> instances= this.discoveryClient.getInstances(Util.SERVICE_LOGISTICA_NAME);
    	
    	if(instances!=null && instances.size()>0) {
    		
    	}
    	
        RestTemplate restTemplate = new RestTemplate();
        String status = restTemplate.getForObject("http://localhost:9010/api/logistica/get-status/xsxs", String.class);
        
  	
    	return status;
    }
    
    
   	public Optional<List<Pedido>> findAllPedidos() {
		
		return Optional.of(repository.findAll());
	}

	public Optional<List<Pedido>> findAllPedidosByStatus(Integer status) {
		
		return Optional.of(repository.findAllByStatus(status));
	}
	
	public Optional<Pedido> findOneById(Integer id) {
		
		return repository.findOneById(id);
	}

	
	/**
	 * @param dto
	 * @return
	 */
	public Optional<PedidoDTO> validaPedido(PedidoDTO dto) {
		
		List<String> erros = new ArrayList<>();
		if(dto.getUserId()==null)
			erros.add("Usuario nao informado");
		if(dto.getDestinatario()==null)
			erros.add("Destinatario nao informado");
		if(dto.getUserEnderecoId()==null)
			erros.add("Endereco de Entrega nao informados");
		if(dto.getItems()==null || dto.getItems().isEmpty())
			erros.add("Items do pedido nao informado.");

		Optional<Usuario> usuario = usuarioReporitory.findOneById(dto.getUserId());
		if(!usuario.isPresent())
			erros.add("Usuario nao cadatrado!");

		Optional<Endereco> endereco = enderecoReporitory.findOneById(dto.getUserEnderecoId());
		if(!endereco.isPresent())
			erros.add("Endereco nao cadatrado!");
		
		
		Double valorTotal = 0.0;
		for(ItemDTO item : dto.getItems()){
			Optional<Produto> produto = produtoReporitory.findOneById(item.getIdProduto());
			
			if(!produto.isPresent())
				erros.add("Produto nao cadatrado: cod:"+item.getIdProduto());
			else
				valorTotal += produto.get().getValor();
		}
		
		dto.setValorTotal(valorTotal);
		
		if(!erros.isEmpty())
			//TO-DO:
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, erros.toString());    
		
		
		return Optional.of(dto);		
	}
	
	public Optional<PedidoDTO> efetivaPedido(PedidoDTO dto) {
		Optional<PedidoDTO> pedidoValidado = validaPedido(dto);
 		
		if(!pedidoValidado.isPresent())
			return Optional.of(null);
		
		Pedido pedido = new Pedido();
		pedido.setDataPedido(new Date());
		pedido.setDestinario(pedidoValidado.get().getDestinatario());
		pedido.setEnderecoEntrega(enderecoReporitory.findOneById(dto.getUserEnderecoId()).get().toString());
		pedido.setUsuario(usuarioReporitory.findOneById(dto.getUserId()).get());
		pedido.setStatus(1);		
		pedido.setValorTotal(pedidoValidado.get().getValorTotal());		
		
		repository.save(pedido);
		
		for(ItemDTO itemDTO : dto.getItems()){
			Optional<Produto> produto = produtoReporitory.findOneById(itemDTO.getIdProduto());
			ItemPedido item = new ItemPedido();
			item.setQuantidade(item.getQuantidade());
			item.setPedido(pedido);
			item.setProduto(produto.get());
			itemPedidoReporitory.save(item);
		}	
		
		return Optional.of(dto);		
	}
	    
}
