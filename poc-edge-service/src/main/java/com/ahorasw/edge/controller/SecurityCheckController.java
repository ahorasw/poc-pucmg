package com.ahorasw.edge.controller;

//Usar somente para teste de perfis de acesso
//Nao deve seguir para producao

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SecurityCheckController {


    @RequestMapping(value="/admin/status", method = RequestMethod.GET)
    public ResponseEntity<Authentication> getTeste(){
        SecurityContext securityContext = SecurityContextHolder.getContext();

    	return new ResponseEntity<>(securityContext.getAuthentication(), HttpStatus.OK);
    }    
    
    @RequestMapping(value="/auth/status", method = RequestMethod.GET)
    public ResponseEntity<Authentication> getTeste2(){
        SecurityContext securityContext = SecurityContextHolder.getContext();

    	return new ResponseEntity<>(securityContext.getAuthentication(), HttpStatus.OK);
    }   
    
    @RequestMapping(value="/pub/status", method = RequestMethod.GET)
    public ResponseEntity<Authentication> getTeste3(){
        SecurityContext securityContext = SecurityContextHolder.getContext();

    	return new ResponseEntity<>(securityContext.getAuthentication(), HttpStatus.OK);
    }  
}