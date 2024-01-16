package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NotificationDTO (
long id,
        @NotNull(message = "Il sender_id' è un campo obbligatorio!")
        long sender_id,
        @NotNull(message = "Il receiver_id è un campo obbligatorio!")
        long receiver_id,
        @NotNull(message = "Lo stato è un campo obbligatorio!")
        String statoNotifica,
        @NotNull(message = "Il product_id non può essere null")
        long product_id

) {
}
