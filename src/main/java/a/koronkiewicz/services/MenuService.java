package a.koronkiewicz.services;

import a.koronkiewicz.entity.Client;
import a.koronkiewicz.entity.Insurance;
import a.koronkiewicz.entity.Phone;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuService {
	private final Scanner scanner = new Scanner(System.in);
	private final PhoneService phoneService = new PhoneService();
	private final ClientService clientService = new ClientService();
	private final InsuranceService insuranceService = new InsuranceService();

	public void showMainMenu() {
		boolean exit = false;
		while (!exit) {
			System.out.println("|==============================|");
			System.out.println("|          Menu Główne         |");
			System.out.println("|==============================|");
			System.out.println("|   1. Telefony                |");
			System.out.println("|   2. Klienci                 |");
			System.out.println("|   3. Ubezpieczenia           |");
			System.out.println("|   0. Wyjdź                   |");
			System.out.println("|==============================|");
			System.out.print("Wybierz z listy: ");

			int userChoice = scanner.nextInt();
			scanner.nextLine();

			switch (userChoice) {
				case 1 -> showPhoneSubMenu();
				case 2 -> showClientSubMenu();
				case 3 -> showInsuranceSubMenu();
				case 0 -> {
					exit = true;
					System.out.println("Do widzenia!");
				}
				default -> System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
			}
			System.out.println();
		}
	}

	public void showPhoneSubMenu() {
		boolean back = false;
		while (!back) {
			System.out.println("|------------------------------------------|");
			System.out.println("|                 Telefony                 |");
			System.out.println("|------------------------------------------|");
			System.out.println("|   [1] Wyświetl wszystkie telefony        |");
			System.out.println("|   [2] Wyświetl telefony wybranej marki   |");
			System.out.println("|   [3] Dodaj telefon                      |");
			System.out.println("|   [4] Usuń telefon                       |");
			System.out.println("|   [0] Cofnij                             |");
			System.out.println("|------------------------------------------|");
			System.out.print("Wybierz z listy: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1 -> printAllPhones();
				case 2 -> printPhoneByBrand();
				case 3 -> addPhone();
				case 4 -> deletePhoneById();
				case 0 -> back = true;
				default -> System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
			}
			System.out.println();
		}
	}

	private void printAllPhones() {
		System.out.println("----- Lista telefonów -----");
		System.out.println("--------------------------------------------------------------");
		System.out.printf("| %-4s | %-15s | %-15s | %-4s | %-7s | %-5s | %-8s \n",
				"Id", "Marka", "Model", "Rok", "Pojemność", "Ekran", "Cena");
		System.out.println("--------------------------------------------------------------");

		for (Phone phone : phoneService.getPhones()) {
			System.out.printf("| %-4s | %-15s | %-15s | %-4s | %-9s | %-5s | %-9s \n",
					phone.id(), phone.brand(), phone.model(), phone.yearOfProduction(),
					phone.driveSize() + "GB", phone.screenSize() + "\"", phone.price() + " PLN");
		}
		System.out.println("--------------------------------------------------------------");
	}

	private void printPhoneByBrand() {
		System.out.print("Wpisz nazwę marki telefonu, którego szukasz: ");
		String phoneBrand = scanner.nextLine();
		List<Phone> phones = phoneService.getPhoneByBrand(phoneBrand);

		if (phones.isEmpty()) {
			System.out.println("Obecnie nie ma telefonu tej marki.");
		} else {
			System.out.println("----- Telefony marki " + phoneBrand + " -----");
			System.out.println("--------------------------------------------------------------");
			System.out.printf("| %-4s | %-15s | %-15s | %-4s | %-7s | %-5s | %-8s \n",
					"Id", "Marka", "Model", "Rok", "Pojemność", "Ekran", "Cena");
			System.out.println("--------------------------------------------------------------");
			for (Phone phone : phones) {
				System.out.printf("| %-4s | %-15s | %-15s | %-4s | %-9s | %-5s | %-9s \n",
						phone.id(), phone.brand(), phone.model(), phone.yearOfProduction(),
						phone.driveSize() + "GB", phone.screenSize() + "\"", phone.price() + " PLN");
			}
			System.out.println("--------------------------------------------------------------");
		}
	}

	private void addPhone() {
		System.out.print("Wpisz markę: ");
		String brand = scanner.nextLine();
		System.out.print("Wpisz model: ");
		String model = scanner.nextLine();
		System.out.print("Wpisz rok produkcji: ");
		int yearOfProduction = scanner.nextInt();
		System.out.print("Wpisz rozmiar pamięci: ");
		int driveSize = scanner.nextInt();
		System.out.print("Wpisz rozmiar ekranu: ");
		double screenSize = scanner.nextDouble();
		System.out.print("Wpisz cenę: ");
		double price = scanner.nextDouble();

		phoneService.addPhone(brand, model, yearOfProduction, driveSize, screenSize, price);
		System.out.println("Telefon dodany pomyślnie!");
	}

	private void deletePhoneById() {
		System.out.print("Podaj Id telefonu, który chcesz usunąć: ");
		int phoneId = scanner.nextInt();

		if (phoneService.deletePhone(phoneId)) {
			System.out.println("Telefon usunięty pomyślnie.");
		} else {
			System.out.println("Nie znaleziono telefonu o podanym Id.");
		}
	}

	public void showClientSubMenu() {
		boolean back = false;
		while (!back) {
			System.out.println("|--------------------------------------|");
			System.out.println("|               Klienci                |");
			System.out.println("|--------------------------------------|");
			System.out.println("|   [1] Wyświetl wszystkich klientów   |");
			System.out.println("|   [2] Dodaj klienta                  |");
			System.out.println("|   [3] Usuń klienta                   |");
			System.out.println("|   [0] Cofnij                         |");
			System.out.println("|--------------------------------------|");
			System.out.print("Wybierz z listy: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1 -> printAllClients();
				case 2 -> addClient();
				case 3 -> deleteClientById();
				case 0 -> back = true;
				default -> System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
			}
			System.out.println();
		}
	}

	private void printAllClients() {
		System.out.println("----- Lista klientów -----");
		System.out.println("----------------------------------------------------");
		System.out.printf("| %-4s | %-20s | %-20s | %-20s \n",
				"Id", "Imię", "Nazwisko", "E-mail");
		System.out.println("----------------------------------------------------");

		for (Client client : clientService.getClients()) {
			System.out.printf("| %-4s | %-20s | %-20s | %-20s \n", +
					client.id(), client.name(), client.surname(), client.email());
		}
		System.out.println("----------------------------------------------------");
	}

	private void addClient() {
		System.out.println("Wprowadź dane klienta: ");
		System.out.print("Imię: ");
		String name = scanner.nextLine();
		System.out.print("Nazwisko: ");
		String surname = scanner.nextLine();
		System.out.print("E-mail: ");
		String email = scanner.nextLine();
		clientService.addClient(name, surname, email);
		System.out.println("Pomyślnie dodano klienta! ");
	}

	private void deleteClientById() {
		System.out.print("Podaj Id klienta, którego chcesz usunąć: ");
		int clientId = scanner.nextInt();

		if (clientService.deleteClient(clientId)) {
			System.out.println("Klient został usunięty.");
		} else {
			System.out.println("Nie znaleziono klienta o podanym Id.");
		}
	}

	private void showInsuranceSubMenu() {
		boolean back = false;
		while (!back) {
			System.out.println("|------------------------------------------|");
			System.out.println("|               Ubezpieczenia              |");
			System.out.println("|------------------------------------------|");
			System.out.println("|   [1] Wyświetl wszystkie ubezpieczenia   |");
			System.out.println("|   [2] Dodaj ubezpieczenie                |");
			System.out.println("|   [3] Usuń ubezpieczenie                 |");
			System.out.println("|   [0] Cofnij                             |");
			System.out.println("|------------------------------------------|");
			System.out.print("Wybierz z listy: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1 -> printAllInsurance();
				case 2 -> addInsurance();
				case 3 -> deleteInsuranceById();
				case 0 -> back = true;
				default -> System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
			}
			System.out.println();
		}
	}

	private void printAllInsurance() {
		System.out.println("----- Lista ubezpieczeń -----");
		System.out.println("----------------------------------------------------------------------------------");
		System.out.printf("| %-4s | %-25s | %-20s | %-30s | %-16s | %-15s \n",
				"Id", "Klient", "E-mail", "Telefon", "Data Rozpoczęcia", "Data Zakończenia");
		System.out.println("----------------------------------------------------------------------------------");

		for (Insurance insurance : insuranceService.getInsurance()) {
			System.out.printf("| %-4s | %-25s | %-20s | %-30s | %-16s | %-15s \n",
					insurance.id(),
					insurance.client().name() + " " + insurance.client().surname(), insurance.client().email(),
					insurance.phone().brand() + " " + insurance.phone().model(),
					insurance.start(), insurance.end());
		}
		System.out.println("----------------------------------------------------------------------------------");
	}

	private void addInsurance() {
		addClient();
		Client client = clientService.getClients().get(clientService.getClients().size() - 1);

		System.out.println("Wybierz telefon z dostępnej listy:");
		printAllPhones();

		System.out.print("Podaj numer telefonu, który chcesz wybrać: ");
		int selectedPhoneId = scanner.nextInt();
		scanner.nextLine();

		Phone selectedPhone = phoneService.getPhoneById(selectedPhoneId);

		if (selectedPhone == null) {
			System.out.println("Nieprawidłowy numer telefonu. Anulowano dodawanie ubezpieczenia.");
			return;
		}

		System.out.print("Czy chcesz ubezpieczyć telefon? (tak/nie): ");
		boolean wantsInsurance = scanner.next().equalsIgnoreCase("tak");

		LocalDate startDate = LocalDate.now();
		System.out.println("Data rozpoczęcia ubezpieczenia: " + startDate);

		LocalDate endDate;
		if (wantsInsurance) {
			System.out.println("Wybierz okres trwania ubezpieczenia:");
			System.out.println("1. 12 miesięcy");
			System.out.println("2. 24 miesiące");
			int durationChoice = scanner.nextInt();
			if (durationChoice == 1) {
				endDate = startDate.plusMonths(12);
			} else if (durationChoice == 2) {
				endDate = startDate.plusMonths(24);
			} else {
				System.out.println("Nieprawidłowy wybór. Ustawiono domyślnie na 12 miesięcy.");
				endDate = startDate.plusMonths(12);
			}
		} else {
			endDate = startDate;
		}

		insuranceService.addInsurance(client, selectedPhone, startDate, endDate);
		System.out.println("Dodano ubezpieczenie.");
	}

	private void deleteInsuranceById() {
		System.out.print("Podaj Id ubezpieczenia, które chcesz usunąć: ");
		int insuranceId = scanner.nextInt();

		if (insuranceService.deleteInsurance(insuranceId)) {
			System.out.println("Ubezpieczenie zostało usunięte.");
		} else {
			System.out.println("Nie znaleziono ubezpieczenia o podanym Id.");
		}
	}
}
