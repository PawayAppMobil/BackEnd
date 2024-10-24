package com.paway.spring.data.kmoneta.transaction.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Document(collection = "transactions")
public class Transaction {

    @Id
    private String id;

    @NotNull(message = "El monto no puede ser nulo.")
    @Min(value = 0, message = "El monto no puede ser negativo.")
    private double amount;

    @NotNull(message = "La fecha no puede ser nula.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotBlank(message = "Los detalles no pueden estar vacíos.")
    @Size(max = 255, message = "Los detalles no pueden exceder los 255 caracteres.")
    private String details;

    private String invoiceId;

    @NotNull(message = "El campo 'isIncome' no puede ser nulo.")
    private boolean isIncome;

    @NotBlank(message = "El userId no puede estar vacío.")
    private String userId;

    public Transaction() {
    }

    public Transaction(int amount, LocalDate date, String details, String invoiceId, boolean isIncome) {
        this.amount = amount;
        this.date = date;
        this.details = details;
        this.invoiceId = invoiceId;
        this.isIncome = isIncome;
    }

    public String getType() {
        return isIncome ? "Income" : "Expense";
    }

    @Override
    public String toString() {
        return "Transaction [id=" + id + ", amount=" + amount + ", date=" + date + ", details=" + details + ", invoiceId=" + invoiceId + ", " + (isIncome ? "Es ingreso" : "Es gasto") + "]";
    }
}
