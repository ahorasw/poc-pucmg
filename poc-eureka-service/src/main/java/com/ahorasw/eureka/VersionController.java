package com.ahorasw.eureka;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
	
    @RequestMapping(value="/version", method = RequestMethod.GET)
    public ResponseEntity<String> selectAll(){
  
    	return new ResponseEntity<>("Eureka Service - V.1.0.0", HttpStatus.OK); 
         
    }

}
