package com.ahorasw.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ahorasw.app.model.Pedido;
import com.ahorasw.app.service.FornecedorService;

@RestController
@RequestMapping(value="api/fornecedor")
public class FornecedorApiController {

	@Autowired 
	FornecedorService service;
	
	@RequestMapping(value = "/ordemEntrega",
			method= RequestMethod.POST,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> incluirOrdemEntrega(@RequestBody Pedido input){
				
		Pedido pedidoProcessado = null;
		
		try {
			pedidoProcessado = service.incluirOrdemEntrega(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>("Erro na inclusao do pedido. "+e.getMessage(), HttpStatus.NOT_MODIFIED);
		}
		
		
		return new ResponseEntity<>(pedidoProcessado, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/ordemEntrega",
			method= RequestMethod.PUT,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> atualizarOrdemEntrega(@RequestBody Pedido input){
				
		Pedido pedidoProcessado = null;
		
		try {
			pedidoProcessado = service.atualizarOrdemEntrega(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>("Erro na atualizacao do pedido. "+e.getMessage(), HttpStatus.NOT_MODIFIED);
		}		
		
		return new ResponseEntity<>(pedidoProcessado, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/ordemEntrega/{idOrdem}",
			method= RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> consultaPedido(@PathVariable("idOrdem") Integer idOrdem){
				
		Pedido pedido = null;
		
		try {
			pedido = service.consultaPedido(idOrdem);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		
		return new ResponseEntity<>(pedido, HttpStatus.OK);
	}
	@RequestMapping(value = "/ordemEntrega/{idOrdem}",
			method= RequestMethod.DELETE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> excluirPedido(@PathVariable("idOrdem") Integer idOrdem){
				
		
		try {
			service.excluirPedido(idOrdem);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_MODIFIED);
		}
		
		
		return new ResponseEntity<>("REGISTRO EXCLUIDO", HttpStatus.ACCEPTED);
	}
}
