package org.iftm.client_api_rest;

import java.lang.StackWalker.Option;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.iftm.client_api_rest.entities.Address;
import org.iftm.client_api_rest.entities.Category;
import org.iftm.client_api_rest.entities.Client;
import org.iftm.client_api_rest.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;

@SpringBootApplication
public class ClientApiRestApplication implements CommandLineRunner{
	
	@Autowired
	private ClientRepository clientRepository;

	public static void main(String[] args) {
		SpringApplication.run(ClientApiRestApplication.class, args);		
	}

	@Override
	public void run(String... args) throws Exception {
		// Criando um instante para a data de nascimento
        			Instant birthDate = Instant.parse("1990-08-21T10:15:30.00Z");

        			// Criando um novo cliente usando o construtor
        			Client client = new Client(1L, "João Silva", "12345678900", 5000.0, birthDate, 2, 
        			new Address(1L, "Rua Senador", "Uberlândia", "Minas Gerais", "38400000"),
        			new Category(1L, "Premium"));

        			Client client2 = new Client(2L, "João Silva", "12345678901", 5000.0, birthDate, 2, 
        			new Address(1L, "Rua Senador", "Uberlândia", "Minas Gerais", "38400000"),
        			new Category(1L, "Premium"));

					clientRepository.save(client);
					clientRepository.save(client2);

					//clientRepository.deleteById(1L);

					Client busca = clientRepository.findById(2L).get();
					System.out.println("\nNome = " + busca.getName());
					System.out.println();

					Page<Client> busca2 = clientRepository.findByName("João Silva");
					if (!busca2.isEmpty()){
						System.out.println("\nQuantidade de pessoas com o nome = " + busca2.getNumberOfElements());
						System.out.println();	
					}else{
						System.out.println("Não existe esse cliente com nome dado.");
					}		
					
					Page<Client> busca3 = clientRepository.findByChildrenBetween(1,3);
					if (!busca3.isEmpty()){
						System.out.println("\nQuantidade de pessoas com 1 a 3 filhos = " + busca3.getNumberOfElements());
						System.out.println();	
					}else{
						System.out.println("Não existe esse cliente com nome dado.");
					}
	}

}
