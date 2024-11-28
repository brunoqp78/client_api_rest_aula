package org.iftm.client_api_rest;

import java.time.Instant;
import java.util.List;

import org.iftm.client_api_rest.entities.Address;
import org.iftm.client_api_rest.entities.Category;
import org.iftm.client_api_rest.entities.Client;
import org.iftm.client_api_rest.repositories.AddressRepository;
import org.iftm.client_api_rest.repositories.CategoryRepository;
import org.iftm.client_api_rest.repositories.ClientRepository;
import org.iftm.client_api_rest.services.AddressService;
import org.iftm.client_api_rest.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class ClientApiRestApplication implements CommandLineRunner {

 	@Autowired
    private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	ClientService clientService;
	@Autowired
	AddressService addressService;

	public static void main(String[] args) {
		SpringApplication.run(ClientApiRestApplication.class, args);
	}

	@Override
	@Transactional // Adiciona a anotação para manter a transação aberta
    public void run(String... args) throws Exception, IllegalArgumentException {
		
		// Criando um instante para a data de nascimento
        Instant birthDate = Instant.parse("1990-08-21T10:15:30.00Z");
		Category cat1 = new Category(1L, "Premium");
		Category cat2 = new Category(2L, "Premium");
        // Criando um novo cliente usando o construtor
        Client client = new Client(1L, "João Silva", "12345678900", 5000.0, birthDate, 2, 
        new Address(1L, "Rua Senador", "Uberlândia", "Minas Gerais", "38400000"),
        cat1);

        Client client2 = new Client(2L, "João Silva Souza", "12345678901", 5000.0, birthDate, 2, 
        new Address(2L, "Rua Senador", "Uberlândia", "Minas Gerais", "38400000"),
        cat2);

		categoryRepository.save(client.getCategory());
		addressRepository.save(client.getAddress());
		clientRepository.save(client);

		categoryRepository.save(client2.getCategory());
		addressRepository.save(client2.getAddress());
		clientRepository.save(client2);
		// Definindo parentesco (ManyToMany)	
		client.getRelatives().add(client2);
		// Aqui estamos fazendo um update no registro do primeiro cliente.
		clientRepository.save(client);
		
		Client busca = clientRepository.findById(1L).get();
		System.out.println("\nCliente : " + busca.getName());
		System.out.println("Endereço (rua): " + busca.getAddress().getStreet());
		System.out.println("Categoria: " + busca.getCategory().getName());		
		System.out.println("Número de parentes :" + busca.getRelatives().size());
		System.out.println();	
		
		String nomeBuscado = "João Silva";
		String nomeParcial = "Silva";
		double salario = 4000;
		List<Client> clientes = clientRepository.findByName(nomeBuscado);
		List<Client> clientes2 = clientRepository.findByIncomeGreaterThan(salario);
		List<Client> clientes3 = clientRepository.findByNameContaining(nomeParcial);
		System.out.println("\n\n------------ Exemplos de Query Methods ----------");
		System.out.println("\nNúmeros de clientes com nome "+nomeBuscado+": " + clientes.size());
		System.out.println("\n\nRelatório de Clientes com nome "+nomeBuscado+": ");
		System.out.println(clientes);
		System.out.println("\nNúmeros de clientes com salario maior que "+salario+": " + clientes2.size());
		System.out.println("\n\nRelatório de Clientes com salario maior que "+salario+": ");
		System.out.println(clientes2);		
		System.out.println("\nNúmeros de clientes com nomes que contenham "+nomeParcial+": " + clientes3.size());
		System.out.println("\n\nRelatório de Clientes com nomes que contenham "+nomeParcial+": ");
		System.out.println(clientes3);		

		System.out.println("-------- Services ------");
		System.out.println(clientService.findAll().toString());
		//para gerar o erro do cep, modifique por um valor incorreto
		// Inserir clientes
		Address address1 = new Address(null, "Rua A", "Cidade X", "Estado Y", "12345-670");

		// Inserir endereços
		addressService.insert(address1);	
		// para gerar o erro da falta de definição de categoria, deixe nulo o parametro.	
		Client client1 = new Client(null, "Alice", "12345678934", 3000.0, null, 2, address1, cat2);
		clientService.insert(client1);

		// Testar recomendação de crédito
		System.out.println(clientService.recommendCredit(client1.getId()));
		

	}	
}
