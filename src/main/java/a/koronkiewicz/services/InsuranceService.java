package a.koronkiewicz.services;

import a.koronkiewicz.entity.Client;
import a.koronkiewicz.entity.Insurance;
import a.koronkiewicz.entity.Phone;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InsuranceService {
	private int newInsuranceId = 0;
	private final List<Insurance> insurances = new ArrayList<>();

	public List<Insurance> getInsurance() {
		return insurances;
	}

	public void addInsurance(Client client, Phone phone, LocalDate start, LocalDate end) {
		Insurance insurance = new Insurance(++newInsuranceId, client, phone, start, end);
		insurances.add(insurance);
	}

	public boolean deleteInsurance(int id) {
		return insurances.removeIf(insurance -> id == insurance.id());
	}
}
