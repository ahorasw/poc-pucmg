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

import com.ahorasw.controlevendas.model.Endereco;
import com.ahorasw.controlevendas.model.ItemPedido;
import com.ahorasw.controlevendas.model.Pedido;
import com.ahorasw.controlevendas.model.Produto;
import com.ahorasw.controlevendas.model.Usuario;
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

   	public Optional<List<Pedido>> findPedidosUsuario(String user) {
   		
   		Optional<Usuario> usuario = usuarioReporitory.findById(user);
		
   		if(!usuario.isPresent())
   			throw new RuntimeException("Usuario nao encontrado!");
		
   		return Optional.of(repository.findAllByUsuario(usuario.get()));
		
	}	
	
	/**
	 * @param dto
	 * @return
	 */
	public Optional<PedidoDTO> validaPedido(PedidoDTO dto, boolean isEfetivacao) {
		
		List<String> erros = new ArrayList<>();
		
		if(dto.getItems()==null || dto.getItems().isEmpty())
			erros.add("Items do pedido nao informado.");

		if(isEfetivacao) {
			if(dto.getUserId()==null)
				erros.add("Usuario nao informado");
	
			Optional<Usuario> usuario = usuarioReporitory.findOneById(dto.getUserId());
			if(!usuario.isPresent())
				erros.add("Usuario nao cadatrado!");

			if(dto.getDestinatario()==null)
				erros.add("Destinatario nao informado");
			
			if(dto.getEnderecoEntrega()==null || dto.getEnderecoEntrega().isEmpty())
				erros.add("Endereco de Entrega nao informados");
		}
		//Optional<Endereco> endereco = enderecoReporitory.findOneById(dto.getUserEnderecoId());
		//if(!endereco.isPresent())
		//	erros.add("Endereco nao cadatrado!");
		
		
		Double valorTotal = 0.0;
		for(ItemDTO item : dto.getItems()){
			Optional<Produto> produto = produtoReporitory.findOneById(item.getIdProduto());
			
			if(item.getQuantidade()==null || item.getQuantidade()==0)
				erros.add("Quantidade do produto nao informada: "+item.getIdProduto());
			
			if(!produto.isPresent())
				erros.add("Produto nao cadatrado: cod:"+item.getIdProduto());
			else
				valorTotal += produto.get().getValor()*item.getQuantidade();
		}
		
		dto.setValorTotal(valorTotal);
		
		if(!erros.isEmpty())
			//TO-DO:
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, erros.toString());    
		
		
		return Optional.of(dto);		
	}
	
	public Optional<Pedido> efetivaPedido(PedidoDTO dto) {
		Optional<PedidoDTO> pedidoValidado = validaPedido(dto, true);
 		
		if(!pedidoValidado.isPresent())
			return Optional.of(null);
		
		Pedido pedido = new Pedido();
		pedido.setDataPedido(new Date());
		pedido.setDestinatario(pedidoValidado.get().getDestinatario());
		pedido.setEnderecoEntrega(dto.getEnderecoEntrega());
		pedido.setUsuario(usuarioReporitory.findOneById(dto.getUserId()).get());
		pedido.setStatus(2);		
		pedido.setValorTotal(pedidoValidado.get().getValorTotal());		
		
		repository.save(pedido);
		
		for(ItemDTO itemDTO : dto.getItems()){
			Optional<Produto> produto = produtoReporitory.findOneById(itemDTO.getIdProduto());
			ItemPedido item = new ItemPedido();
			item.setQuantidade(itemDTO.getQuantidade());
			item.getPk().setPedido(pedido);
			item.getPk().setProduto(produto.get());
			itemPedidoReporitory.save(item);
		}	
		
		return Optional.of(pedido);		
	}
	    
}
