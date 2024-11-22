package org.iftm.client_api_rest;

import java.time.Instant;

import org.iftm.client_api_rest.entities.Address;
import org.iftm.client_api_rest.entities.Category;
import org.iftm.client_api_rest.entities.Client;

public class Main {
    public static void main(String[] args) {
        // Criando um instante para a data de nascimento
        			Instant birthDate = Instant.parse("1990-08-21T10:15:30.00Z");

        			// Criando um novo cliente usando o construtor
        			Client client = new Client(1L, "João Silva", "12345678900", 5000.0, birthDate, 2, 
        			new Address(1L, "Rua Senador", "Uberlândia", "Minas Gerais", "38400000"),
        			new Category(1L, "Premium"));

        			Client client2 = new Client(2L, "João Silva segundo", "12345678901", 5000.0, birthDate, 2, 
        			new Address(1L, "Rua Senador", "Uberlândia", "Minas Gerais", "38400000"),
        			new Category(1L, "Premium"));
    }
}
