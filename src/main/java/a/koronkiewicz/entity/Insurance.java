package a.koronkiewicz.entity;

import java.time.LocalDate;

public record Insurance(int id, Client client, Phone phone, LocalDate start, LocalDate end) {
}
