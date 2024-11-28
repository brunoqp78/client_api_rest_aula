package org.iftm.client_api_rest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.iftm.client_api_rest.entities.Address;
import org.iftm.client_api_rest.entities.Category;
import org.iftm.client_api_rest.entities.Client;
import org.iftm.client_api_rest.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Regra: Listar todos os clientes
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    // Regra: Buscar cliente por ID
    @Transactional(readOnly = true)
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    // Regra: Buscar cliente por ID
    @Transactional(readOnly = true)
    public List<Client> findByName(String name) {
        return clientRepository.findByName(name);
    }


    // Regra: Inserir cliente com validações
    @Transactional
    public Client insert(Client client) {
        validateIncome(client.getIncome());
        validateChildren(client.getChildren());
        validateCategory(client.getCategory());
        return clientRepository.save(client);
    }

    // Regra: Atualizar cliente com validações
    @Transactional
    public Client update(Long id, Client updatedClient) {
    Client entity = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
        validateIncome(updatedClient.getIncome());
        validateChildren(updatedClient.getChildren());
        validateCategory(updatedClient.getCategory());
        entity.setName(updatedClient.getName());
        entity.setCpf(updatedClient.getCpf());
        entity.setIncome(updatedClient.getIncome());
        entity.setBirthDate(updatedClient.getBirthDate());
        entity.setChildren(updatedClient.getChildren());
        return clientRepository.save(entity);
    }

    // Regra: Deletar cliente
    @Transactional
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    // Regra de validação: Verificar renda mínima
    private void validateIncome(Double income) {
        if (income < 1000.0) {
            throw new IllegalArgumentException("Income must be at least R$ 1,000.00");
        }
    }

    // Regra de validação: Limitar número de filhos
    private void validateChildren(Integer children) {
        if (children > 10) {
            throw new IllegalArgumentException("Clients can have at most 10 children.");
        }
    }

    // Regra adicional: Recomendar crédito com base em renda e filhos
    public String recommendCredit(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
        double factor = client.getIncome() / (client.getChildren() + 1);
        if (factor > 5000) {
            return "High credit limit recommended.";
        } else if (factor > 2000) {
            return "Medium credit limit recommended.";
        } else {
            return "Low credit limit recommended.";
        }
    }

    // Regra adicional : A categoria é obrigatória nesse contexto
    private void validateCategory(Category category) {
        
        if (category == null) {
            throw new IllegalArgumentException("Client must be associated with a category.");
        }
    }

    @Transactional
    private void saveClients(ArrayList<Client> clients){
        clientRepository.saveAll(clients);
    }
}