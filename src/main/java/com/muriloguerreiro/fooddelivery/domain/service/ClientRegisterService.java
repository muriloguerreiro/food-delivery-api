package com.muriloguerreiro.fooddelivery.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muriloguerreiro.fooddelivery.domain.model.Client;
import com.muriloguerreiro.fooddelivery.domain.repository.ClientRepository;

@Service
public class ClientRegisterService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Client save(Client client) {
		return clientRepository.save(client);
	}
	
	public void delete(Long clientId) {
		clientRepository.deleteById(clientId);
	}
}
