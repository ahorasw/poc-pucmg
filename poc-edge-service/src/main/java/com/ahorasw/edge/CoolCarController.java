package com.ahorasw.edge;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CoolCarController {
    private CarRepository repository;

    public CoolCarController(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/auth/cool-cars")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<Car> coolCars() {
        return repository.findAll().stream()
                .filter(this::isCool)
                .collect(Collectors.toList());
    }

    private boolean isCool(Car car) {
        return !car.getName().equals("AMC Gremlin") &&
                !car.getName().equals("Triumph Stag") &&
                !car.getName().equals("Ford Pinto") &&
                !car.getName().equals("Yugo GV");
    }
    
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