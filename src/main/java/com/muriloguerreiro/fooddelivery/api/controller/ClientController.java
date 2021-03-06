package com.muriloguerreiro.fooddelivery.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.muriloguerreiro.fooddelivery.domain.exception.DomainException;
import com.muriloguerreiro.fooddelivery.domain.model.Client;
import com.muriloguerreiro.fooddelivery.domain.repository.ClientRepository;
import com.muriloguerreiro.fooddelivery.domain.service.ClientRegisterService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientRegisterService clientRegisterService;
	
	@GetMapping
	public List<Client> listClients() {
		return clientRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> findClient(@PathVariable Long id) {
		Optional<Client> client = clientRepository.findById(id);
		
		if (client.isPresent()) {
			return ResponseEntity.ok(client.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client createClient(@Valid @RequestBody Client client) {
		Client existingClient = clientRepository.findByEmail(client.getEmail());
		
		if (existingClient != null && !existingClient.equals(client)) {
			throw new DomainException("Informed email is already registered.");
		}
		
		return clientRegisterService.save(client);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Client> updateClient(@Valid @PathVariable Long id, @RequestBody Client client) {
		
		if(!clientRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		client.setId(id);
		client = clientRegisterService.save(client);
		
		return ResponseEntity.ok(client);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
		
		if (!clientRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		clientRegisterService.delete(id);
		
		return ResponseEntity.noContent().build();
	}

}
