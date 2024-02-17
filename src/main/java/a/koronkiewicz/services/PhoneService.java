package a.koronkiewicz.services;

import a.koronkiewicz.entity.Phone;

import java.util.ArrayList;
import java.util.List;

public class PhoneService {
	private final List<Phone> phones = generatePhones();

	public List<Phone> generatePhones() {
		List<Phone> phoneList = new ArrayList<>();

		Phone phone1 = new Phone(1, "Samsung", "Galaxy S20", 2020, 128, 6.0, 1250.99);
		Phone phone2 = new Phone(2, "Samsung", "Galaxy Z Fold 3", 2021, 512, 7.6, 1999.99);
		Phone phone3 = new Phone(3, "LG", "G8 ThinQ", 2019, 128, 6.1, 899.99);
		Phone phone4 = new Phone(4, "Nokia", "3310", 2000, 1, 0.4, 50.0);
		Phone phone5 = new Phone(5, "Nokia", "Lumia 920", 2012, 32, 4.5, 300.0);
		Phone phone6 = new Phone(6, "LG", "Velvet", 2020, 256, 6.8, 899.99);

		phoneList.add(phone1);
		phoneList.add(phone2);
		phoneList.add(phone3);
		phoneList.add(phone4);
		phoneList.add(phone5);
		phoneList.add(phone6);

		return phoneList;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public Phone getPhoneById(int id) {
		return phones.stream()
				.filter(phone -> phone.id() == id)
				.findFirst()
				.orElse(null);
	}

	public List<Phone> getPhoneByBrand(String brand) {
		List<Phone> matchingPhone = new ArrayList<>();

		for (Phone phone : phones) {
			if (phone.brand().equalsIgnoreCase(brand)) {
				matchingPhone.add(phone);
			}
		}
		return matchingPhone;
	}

	public Phone addPhone(String brand, String model, int yearOfProduction,
						  int driveSize, double screenSize, double price) {

		int newPhoneId = phones.stream()
				.mapToInt(Phone::id)
				.max()
				.orElse(0) + 1;

		Phone phone = new Phone(newPhoneId, brand, model, yearOfProduction, driveSize, screenSize, price);
		phones.add(phone);

		return phone;
	}

	public boolean deletePhone(int id) {
		return phones.removeIf(phone -> id == phone.id());
	}
}
