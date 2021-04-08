package com.muriloguerreiro.fooddelivery.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muriloguerreiro.fooddelivery.domain.model.Client;

@RestController
public class ClientController {
	
	@GetMapping("/clients")
	public List<Client> list() {
		var client1 = new Client();
		client1.setId(1L);
		client1.setName("João");
		client1.setDocument("442.274.634-59");
		client1.setPhone("31 99999-1111");
		client1.setBirth("2001-04-16");
		client1.setEmail("joaozinho@teste.com");
		client1.setPassword("passjoao");
		
		var client2 = new Client();
		client2.setId(2L);
		client2.setName("Maria");
		client2.setDocument("412.549.257-83");
		client2.setPhone("34 99977-1234");
		client2.setBirth("1998-06-02");
		client2.setEmail("mariazinha@teste.com");
		client2.setPassword("passmaria");
		
		return Arrays.asList(client1,client2);
	}

}
