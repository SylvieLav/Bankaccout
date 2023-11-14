package fr.arolla.main;

import java.time.LocalDate;

public record Operation(OperationType operationType, LocalDate localDate, Integer amount) {
}
