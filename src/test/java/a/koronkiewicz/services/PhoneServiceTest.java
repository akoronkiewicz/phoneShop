package a.koronkiewicz.services;

import a.koronkiewicz.entity.Phone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PhoneServiceTest {
	private PhoneService phoneService;

	@BeforeEach
	void setUp() {
		phoneService = new PhoneService();
	}

	@Test
	void getPhonesShouldReturnListOfPhones() {
		//given

		//when
		List<Phone> phones = phoneService.getPhones();

		//then
		assertThat(phones).isNotNull();
	}

	@Test
	void getPhoneByIdShouldReturnPhone() {
		//given

		//when
		Phone phone = phoneService.getPhoneById(2);

		//then
		assertThat(phone).isNotNull();
		assertThat(phone.id()).isEqualTo(2);
	}

	@Test
	void getPhoneByIdShouldReturnNullWhenPhoneIdIsIncorrect() {
		//given

		//when
		Phone phone = phoneService.getPhoneById(99);

		//then
		assertThat(phone).isNull();
	}

	@Test
	void getPhoneByBrandShouldReturnListOfPhones() {
		//given

		//when
		List<Phone> brandPhone = phoneService.getPhoneByBrand("Samsung");

		//then
		assertThat(brandPhone).isNotNull();
	}

	@Test
	void addPhoneShouldAddPhoneToList() {
		//given

		//when
		Phone phone = phoneService.addPhone("Motorola", "Blade",
				2020, 128, 6.0, 999.99);

		//then
		assertThat(phone).isEqualTo(new Phone(7, "Motorola", "Blade",
				2020, 128, 6.0, 999.99));
	}

	@Test
	void deletePhoneShouldRemovePhoneFromList() {
		//given

		//when
		boolean result = phoneService.deletePhone(1);

		//then
		assertThat(result).isTrue();
	}

	@Test
	void deletePhoneNonExistingPhoneShouldReturnFalse() {
		//given

		//when
		boolean result = phoneService.deletePhone(99);

		//then
		assertThat(result).isFalse();
		assertThat(phoneService.getPhones()).isNotEmpty();
	}
}
