package org.iftm.client_api_rest.repositories;

import java.util.List;

import org.iftm.client_api_rest.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Long> {  
    public Page<Client> findByName(String name);

    public Page<Client> findByChildrenBetween(int quantidadeMinima, int quantidadeMaxima);
} 
