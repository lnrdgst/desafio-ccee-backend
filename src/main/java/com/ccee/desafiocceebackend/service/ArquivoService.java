package com.ccee.desafiocceebackend.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ccee.desafiocceebackend.entity.Arquivo;
import com.ccee.desafiocceebackend.repository.ArquivoRepository;

@Component
public class ArquivoService {
	
private final ArquivoRepository arquivoRepository;
	
	public ArquivoService(ArquivoRepository arquivoRepository) {
        this.arquivoRepository = arquivoRepository;
    }
	
	public List<Arquivo> buscarTodos(){
		return this.arquivoRepository.findAll();
	}

}
