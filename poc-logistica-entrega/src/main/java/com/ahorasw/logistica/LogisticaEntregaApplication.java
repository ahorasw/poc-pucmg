package com.ahorasw.logistica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
@RestController
public class LogisticaEntregaApplication {

	@RequestMapping("/get-status/{idPedido}")
    public String serviceInstancesByApplicationName(@PathVariable String idPedido) {
        return "Pedido="+idPedido+" => Status=Em preparacao";
    }
	
	
	public static void main(String[] args) {
		SpringApplication.run(LogisticaEntregaApplication.class, args);
	}
	
}


