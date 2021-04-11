package com.muriloguerreiro.fooddelivery.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muriloguerreiro.fooddelivery.domain.model.Client;
import com.muriloguerreiro.fooddelivery.domain.repository.ClientRepository;

@RestController
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@GetMapping("/clients")
	public List<Client> list() {
		return clientRepository.findAll();
	}

}
