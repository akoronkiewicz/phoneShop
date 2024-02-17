package a.koronkiewicz.services;

import a.koronkiewicz.entity.Client;
import a.koronkiewicz.entity.Insurance;
import a.koronkiewicz.entity.Phone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class InsuranceServiceTest {
	private InsuranceService insuranceService;

	@BeforeEach
	void setUp() {
		insuranceService = new InsuranceService();
	}

	@Test
	void getInsuranceShouldReturnEmptyListWhenNoInsuranceAdded() {
		//given

		//when
		List<Insurance> insurances = insuranceService.getInsurance();

		//then
		assertThat(insurances).isEmpty();
	}

	@Test
	void getInsuranceShouldReturnListOfInsurance() {
		//given
		Insurance insurance = new Insurance(1,
				new Client(1, "Jan", "Nowak", "nowak@mail.com"),
				new Phone(2, "Samsung", "Galaxy Z Fold 3", 2021, 512,
						7.6, 1999.99), LocalDate.now(), LocalDate.now().plusMonths(12));

		//when
		insuranceService.addInsurance(
				new Client(1, "Jan", "Nowak", "nowak@mail.com"),
				new Phone(2, "Samsung", "Galaxy Z Fold 3", 2021, 512,
						7.6, 1999.99), LocalDate.now(), LocalDate.now().plusMonths(12));
		List<Insurance> insurances = insuranceService.getInsurance();

		//then
		assertThat(insurances)
				.isNotNull()
				.hasSize(1)
				.contains(insurance);
	}

	@Test
	void addInsuranceShouldAddInsuranceToList() {
		//given
		Client client = new Client(1, "Jan", "Nowak", "nowak@mail.com");
		Phone phone = new Phone(2, "Samsung", "Galaxy Z Fold 3",
				2021, 512, 7.6, 1999.99);
		LocalDate start = LocalDate.now();
		LocalDate end = start.plusMonths(12);

		//when
		insuranceService.addInsurance(client, phone, start, end);

		//then
		assertThat(insuranceService.getInsurance())
				.hasSize(1)
				.contains(new Insurance(1, client, phone, start, end));
	}

	@Test
	void deleteInsuranceShouldRemoveInsuranceFromList() {
		//given
		Client client = new Client(1, "Jan", "Nowak", "nowak@mail.com");
		Phone phone = new Phone(2, "Samsung", "Galaxy Z Fold 3",
				2021, 512, 7.6, 1999.99);
		LocalDate start = LocalDate.now();
		LocalDate end = start.plusMonths(12);

		insuranceService.addInsurance(client, phone, start, end);

		//when
		insuranceService.deleteInsurance(1);

		//then
		assertThat(insuranceService.getInsurance()).isEmpty();
	}

	@Test
	void deleteNonExistingInsurance() {
		//given
		Client client = new Client(1, "Jan", "Nowak", "nowak@mail.com");
		Phone phone = new Phone(2, "Samsung", "Galaxy Z Fold 3",
				2021, 512, 7.6, 1999.99);
		LocalDate start = LocalDate.now();
		LocalDate end = start.plusMonths(12);

		insuranceService.addInsurance(client, phone, start, end);

		//when
		boolean result = insuranceService.deleteInsurance(99);

		//then
		assertThat(result).isFalse();
		assertThat(insuranceService.getInsurance()).isNotEmpty();
	}

	@Test
	void deleteInsuranceWhenListIsEmptyShouldReturnFalse() {
		//given

		//when
		boolean result = insuranceService.deleteInsurance(1);

		//then
		assertThat(result).isFalse();
	}
}
