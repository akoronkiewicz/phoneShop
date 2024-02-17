package a.koronkiewicz.services;

import a.koronkiewicz.entity.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ClientServiceTest {
	private ClientService clientService;

	@BeforeEach
	void setUp() {
		clientService = new ClientService();
	}

	@Test
	void getClientShouldReturnEmptyListWhenNoClientAdded() {
		//given

		//when
		List<Client> clients = clientService.getClients();

		//then
		assertThat(clients).isEmpty();
	}

	@Test
	void getClientsShouldReturnListOfClients() {
		//given
		Client client = new Client(1, "Jan", "Nowak", "nowak@mail.com");

		//when
		clientService.addClient("Jan", "Nowak", "nowak@mail.com");
		List<Client> clients = clientService.getClients();

		//then
		assertThat(clients)
				.isNotNull()
				.hasSize(1)
				.contains(client);
	}

	@Test
	void addClientShouldAddClientToList() {
		//given

		//when
		Client client = clientService.addClient("Jan", "Nowak", "nowak@mail.com");

		//then
		assertThat(client).isEqualTo(new Client(1, "Jan", "Nowak", "nowak@mail.com"));
	}

	@Test
	void deleteClientShouldRemoveClientFromList() {
		//given
		clientService.addClient("Jan", "Nowak", "nowak@mail.com");

		//when
		boolean result = clientService.deleteClient(1);

		//then
		assertThat(result).isTrue();
		assertThat(clientService.getClients()).isEmpty();
	}

	@Test
	void deleteNonExistingClientShouldReturnFalse() {
		//given
		clientService.addClient("Jan", "Nowak", "nowak@mail.com");

		//when
		boolean result = clientService.deleteClient(99);

		//then
		assertThat(result).isFalse();
		assertThat(clientService.getClients()).isNotEmpty();
	}

	@Test
	void deleteClientWhenListIsEmptyShouldReturnFalse() {
		//given

		// when
		boolean result = clientService.deleteClient(1);

		// then
		assertThat(result).isFalse();
	}
}
