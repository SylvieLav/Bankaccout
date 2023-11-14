package fr.arolla.bankaccount.model;

import java.time.LocalDate;

public record Operation(OperationType operationType, LocalDate localDate, Integer amount) {
}
