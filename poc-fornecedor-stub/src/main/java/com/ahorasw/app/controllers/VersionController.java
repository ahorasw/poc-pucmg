package com.ahorasw.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
	
    @RequestMapping(value="/version", method = RequestMethod.GET)
    public ResponseEntity<String> selectAll(){
  
    	return new ResponseEntity<>("Fornecedor Mock - V.1.0.0", HttpStatus.OK); 
         
    }

}
