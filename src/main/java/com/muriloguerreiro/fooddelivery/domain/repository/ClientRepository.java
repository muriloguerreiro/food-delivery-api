package com.muriloguerreiro.fooddelivery.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.muriloguerreiro.fooddelivery.domain.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	//List<Client> findByName(String name);
	//List<Client> findByNameContaining(String name);
}
