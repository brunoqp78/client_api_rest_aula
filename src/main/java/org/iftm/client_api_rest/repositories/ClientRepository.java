package org.iftm.client_api_rest.repositories;

import java.util.List;

import org.iftm.client_api_rest.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {  
    // Aqui, o JpaRepository já cria métodos como save(), findById(), deleteById(), entre outros.


    public List<Client> findByName(String string);

    public List<Client> findByIncomeGreaterThan(double salario);

    public List<Client> findByNameContaining(String nomeParcial);
} 
