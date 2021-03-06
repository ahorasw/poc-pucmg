package com.ahorasw.controlevendas.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ahorasw.controlevendas.model.Pedido;
import com.ahorasw.controlevendas.service.PedidoService;
import com.ahorasw.controlevendas.service.dto.PedidoDTO;


@RestController
public class PedidoController {
	

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    
    @RequestMapping(value="/admin/pedido", method = RequestMethod.GET)
	public ResponseEntity<List<Pedido>> getListaPedidos() {
		
    	Optional<List<Pedido>> pedidos = pedidoService.findAllPedidos();
    	if(pedidos.isPresent() && !pedidos.get().isEmpty()) {
    		return new ResponseEntity<>(pedidos.get(), HttpStatus.OK); 
    	}else {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedidos nao encontrados");    
    	}
    	
	}
    
    @RequestMapping(value="/auth/pedido/{user}", method = RequestMethod.GET)
	public ResponseEntity<List<Pedido>> getListaPedidosUsuario(@PathVariable String user) {
		
    	Optional<List<Pedido>> pedidos = pedidoService.findPedidosUsuario(user);
    	if(pedidos.isPresent() && !pedidos.get().isEmpty()) {
    		return new ResponseEntity<>(pedidos.get(), HttpStatus.OK); 
    	}else {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedidos nao encontrados");    
    	}    	
	}    
    
    @RequestMapping(value="/auth/pedido/status/{status}", method = RequestMethod.GET)
	public ResponseEntity<List<Pedido>> findAllPedidosByStatus(@PathVariable Integer status) {
		
    	Optional<List<Pedido>> pedidos = pedidoService.findAllPedidosByStatus(status);
    	if(pedidos.isPresent() && !pedidos.get().isEmpty()) {
    		return new ResponseEntity<>(pedidos.get(), HttpStatus.OK); 
    	}else {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedidos nao encontrados");    
    	}
	}
    
	@RequestMapping("/auth/pedido/get-status/{id}")
	public ResponseEntity<Integer>  getStatus(@PathVariable Integer id) {
    	
		Optional<Pedido> pedido = pedidoService.findOneById(id);
		
    	if(pedido.isPresent()) {
    		return new ResponseEntity<>(pedido.get().getStatus(), HttpStatus.OK); 
    	}else {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido nao encontrado");    
    	}
    	
	}

	@RequestMapping(value="/pub/pedido/validar", method = RequestMethod.POST)
	public ResponseEntity<PedidoDTO> validaPedido(@RequestBody PedidoDTO dto) {
		
		Optional<PedidoDTO> pedido = pedidoService.validaPedido(dto, false);
		
    	try{
    		if(pedido.isPresent()) 
    			return new ResponseEntity<>(pedido.get(), HttpStatus.ACCEPTED); 
    		else
    			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Erro na validacao do Pedido");    
    			
    	}catch(Exception e) {
    		throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Pedido possui erros!\nErros:\n"+e.getMessage());    
			
		}

	}
	@RequestMapping(value="/auth/pedido/efetivar", method = RequestMethod.POST)
	public ResponseEntity<Pedido> efetivaPedido(@RequestBody PedidoDTO dto) {
		
		Optional<Pedido> pedido = pedidoService.efetivaPedido(dto);
		
		try {
	    	if(pedido.isPresent()) 
	    		return new ResponseEntity<>(pedido.get(), HttpStatus.CREATED); 
	    	else 
	    		throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Nao foi possivel Efetivar o Pedido");    	    	
		}catch(Exception e) {
    		throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Pedido possui erros!\nErros:\n"+e.getMessage());    
			
		}
	}	
	
	
}
