package com.ahorasw.controlevendas.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ahorasw.controlevendas.domain.Endereco;
import com.ahorasw.controlevendas.domain.Fornecedor;
import com.ahorasw.controlevendas.repository.FornecedorRepository;

@RestController
public class FornecedorController {
	
	@Autowired 
	FornecedorRepository repository;

    private final Logger log = LoggerFactory.getLogger(FornecedorController.class);


    @RequestMapping(value="/fornecedor", method = RequestMethod.GET)
    public ResponseEntity<List<Fornecedor>> selectAll(){
    	log.debug("selectALL fornecedor executado");
        
    	Optional<List<Fornecedor>> fornecedores = Optional.of(repository.findAll());
        if(fornecedores.isPresent())
        	return new ResponseEntity<>(fornecedores.get(), HttpStatus.OK); 
        else
 		   throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Fornecedor Not Found");
       
    }

    
    @RequestMapping(value="/fornecedor/{id}", method = RequestMethod.GET)
    public ResponseEntity<Fornecedor> getFornecedor(@PathVariable("id") int id){

    	log.debug("select fornecedor executado");
    	Optional<Fornecedor> fornecedor = repository.findOneById(id);
        if(fornecedor.isPresent())
        	return new ResponseEntity<>(fornecedor.get(), HttpStatus.OK); 
        else
 		   throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Fornecedor Not Found");

    }

    @RequestMapping(value="/fornecedor",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Fornecedor> insert(@RequestBody Fornecedor dto){
    	log.debug("insert fornecedor executado");
        
    	repository.save(dto);

      	return new ResponseEntity<>(dto, HttpStatus.CREATED); 

    }


    @RequestMapping(value="/fornecedor/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id){
       log.debug("delete fornecedor executado");

        
       
       Optional<Fornecedor> fornecedor = repository.findOneById(id);
	   if(fornecedor.isPresent()) {
		   repository.delete(fornecedor.get());
	   }else {
		   throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Fornecedor Not Found");
		   
	   }       
    }
    

	
}
