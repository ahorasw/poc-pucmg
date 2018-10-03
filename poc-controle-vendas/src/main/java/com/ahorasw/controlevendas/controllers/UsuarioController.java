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

import com.ahorasw.controlevendas.model.Endereco;
import com.ahorasw.controlevendas.model.Usuario;
import com.ahorasw.controlevendas.repository.EnderecoRepository;
import com.ahorasw.controlevendas.repository.UsuarioRepository;

@RestController
public class UsuarioController {
	
	@Autowired 
	UsuarioRepository repository;
	
	@Autowired 
	EnderecoRepository enderecoRepository;

    private final Logger log = LoggerFactory.getLogger(UsuarioController.class);


    @RequestMapping(value="/pub/usuario", method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> selectAll(){
    	log.debug("selectALL usuario executado");
        
    	Optional<List<Usuario>> usuarios = Optional.of(repository.findAll());
        if(usuarios.isPresent())
        	return new ResponseEntity<>(usuarios.get(), HttpStatus.OK); 
        else
    	    throw new ResponseStatusException(
 			          HttpStatus.NOT_FOUND, "Usuariosß Not Found");  
       
    }

    
    @RequestMapping(value="/usuario/{id}", method = RequestMethod.GET)
    public ResponseEntity<Usuario> getUsuario(@PathVariable("id") String id){

    	log.debug("select usuario executado");

       	Optional<Usuario> usuario = repository.findOneById(id);
       	if(usuario.isPresent())
        	return new ResponseEntity<>(usuario.get(), HttpStatus.OK); 
        else
    	    throw new ResponseStatusException(
   			          HttpStatus.NOT_FOUND, "Usuario Not Found");        

    }

    @RequestMapping(value="/usuario", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> insert(@RequestBody Usuario dto){
    	log.debug("insert usuario executado");
        
    	repository.save(dto);

      	return new ResponseEntity<>(dto, HttpStatus.CREATED); 

    }


    @RequestMapping(value="/usuario", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id){
       log.debug("delete usuario executado");

           
       	Optional<Usuario> usuario = repository.findOneById(id);
       	if(usuario.isPresent())
       		repository.delete(usuario.get());
        else
    	    throw new ResponseStatusException(
   			          HttpStatus.NOT_FOUND, "Usuario Not Found");
       	
        
    }
    
 //   @RequestMapping(value="/pub/usuario/status", method = RequestMethod.GET)
 //   @PreAuthorize("hasAuthority('admin')")
 //   public ResponseEntity<Authentication> getTeste(){
 //       SecurityContext securityContext = SecurityContextHolder.getContext();
 //   	return new ResponseEntity<>(securityContext.getAuthentication(), HttpStatus.OK);
 //   } 
    
    //Enderecos
    @RequestMapping(value="/pub/usuario/{id}/endereco", method = RequestMethod.GET)
    public ResponseEntity<List<Endereco>> getAllEnderecos(@PathVariable("id") String userId){

    	log.debug("select usuario executado");
    	Optional<Usuario> usuario = repository.findOneById(userId);
    	if(usuario.isPresent())
        	return new ResponseEntity<>(enderecoRepository.findAllByUsuario(usuario.get()), HttpStatus.OK); 
        else
  		   throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Usuario Not Found");

    }
    
    @RequestMapping(value="/usuario/{id}/endereco", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Endereco> insert(@PathVariable("id") String userId, @RequestBody Endereco dto){
    	log.debug("insert endereco do usuario executado");
 
       Optional<Usuario> user = repository.findOneById(userId);
 	   if(user.isPresent()) {
        	dto.setUsuario(user.get()); 
            enderecoRepository.save(dto);
 		   return new ResponseEntity<>(dto, HttpStatus.CREATED); 
 	   }else {
 		   throw new ResponseStatusException(
 			          HttpStatus.NOT_FOUND, "Usuario Not Found");
 		   
 	   }    	


    }
    
    @RequestMapping(value="/usuario/{id}/endereco/{idendereco}", method = RequestMethod.GET)
    public ResponseEntity<Endereco> findEndereco(@PathVariable("id") String id, @PathVariable("idendereco") int idEndereco){
       log.debug("delete usuario executado");

       
       Optional<Endereco> endereco = enderecoRepository.findOneById(idEndereco);
	   if(endereco.isPresent() && endereco.get().getUsuario().getId().equals(id)) {
		   return new ResponseEntity<>(endereco.get(), HttpStatus.OK); 
	   }else {
		   throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Endereco Not Found");
		   
	   }
              
    }    
    
    @RequestMapping(value="/usuario/{id}/endereco/{idendereco}", method = RequestMethod.DELETE)
     public void delete(@PathVariable("id") String id, @PathVariable("idendereco") int idEndereco) {
       log.debug("delete usuario executado");

       Optional<Endereco> endereco = enderecoRepository.findOneById(idEndereco);
	   if(endereco.isPresent() && endereco.get().getUsuario().getId().equals(id)) {
		   enderecoRepository.delete(endereco.get());
	   }else {
		   throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Endereco Not Found");
		   
	   }
       
     
    }
        
}
