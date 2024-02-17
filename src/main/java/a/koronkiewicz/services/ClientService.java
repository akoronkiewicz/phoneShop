package a.koronkiewicz.services;

import a.koronkiewicz.entity.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientService {
	private int newClientId = 0;
	private final List<Client> clients = new ArrayList<>();

	public List<Client> getClients() {
		return clients;
	}

	public Client addClient(String name, String surname, String email) {
		Client client = new Client(++newClientId, name, surname, email);
		clients.add(client);
		return client;
	}

	public boolean deleteClient(int id) {
		return clients.removeIf(client -> id == client.id());
	}
}
