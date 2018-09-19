package com.ahorasw.logistica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahorasw.logistica.repository.OrdemEntregaRepository;

@Service
public class LogisticaService {
	
	@Autowired
	OrdemEntregaRepository oeRepository;
	
	
}
