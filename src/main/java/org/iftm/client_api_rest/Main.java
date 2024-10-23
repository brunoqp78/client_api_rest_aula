package org.iftm.client_api_rest;

import java.time.Instant;

import org.iftm.client_api_rest.entities.Address;
import org.iftm.client_api_rest.entities.Client;

public class Main {
    public static void main(String[] args) {
        Client client1 = new Client(1L, "Alice", "123.456.789-00", 5000.0, Instant.parse("1990-01-01T00:00:00Z"), 2, new Address());
        Client client2 = new Client(2L, "Bob", "987.654.321-00", 7000.0, Instant.parse("1985-05-15T00:00:00Z"), 3, new Address());
        System.out.println("Client 1: " + client1.getName() + ", CPF: " + client1.getCpf());
        System.out.println("Client 2: " + client2.getName() + ", CPF: " + client2.getCpf());
    }
}
