package com.ahorasw.controlevendas.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ahorasw.controlevendas.model.Produto;
import com.ahorasw.controlevendas.service.ProdutoService;


@RestController
public class ProdutoController {


    private final Logger log = LoggerFactory.getLogger(ProdutoController.class);

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @RequestMapping(value="/pub/produto", method = RequestMethod.GET)
    public ResponseEntity<List<Produto>> selectAll(){
    	log.debug("selectALL produto executado");
        
    	Optional<List<Produto>> produtos = produtoService.getAllProdutos();
        if(produtos.isPresent())
        	return new ResponseEntity<>(produtos.get(), HttpStatus.OK); 
        else
		    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado");     
       
    }

    @RequestMapping(value="/pub/produto/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> getProduto(@PathVariable("id") int id){

    	log.debug("select produto executado");
    	Optional<Produto> produto = produtoService.getProdutoById(id);
        if(produto.isPresent())
        	return new ResponseEntity<>(produto.get(), HttpStatus.OK); 
        else
		    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado");     

    }

    @RequestMapping(value="/adm/produto", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produto> insert(@RequestBody Produto dto){
    	log.debug("insert produto executado");
        
    	Optional<Produto> produto = produtoService.incluirProduto(dto);
        if(produto.isPresent())
        	return new ResponseEntity<>(produto.get(), HttpStatus.CREATED); 
        else
  		   throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Erro na inclusao do Produto");   
    }
    

    @RequestMapping(value="/adm/produto", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produto> update(@RequestBody Produto dto){
    	log.debug("update produto executado");
    	Optional<Produto> produto = produtoService.atualizaProduto(dto);
        if(produto.isPresent())
        	return new ResponseEntity<>(produto.get(), HttpStatus.CREATED); 
        else
   		   throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Erro na atualizacao do Produto");     
    }
    

    @RequestMapping(value="/adm/produto/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id){
       log.debug("delete produto executado");

	    Optional<Produto> produto = produtoService.getProdutoById(id);
	    if(produto.isPresent())
	        produtoService.delete(id);
	    else
		    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado");         
    }
    
}