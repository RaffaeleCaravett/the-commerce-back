package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NotificationResponseDTO(

        @NotNull(message = "L'id non può essere null")
        long id,
        @NotNull(message = "Il sender_id' è un campo obbligatorio!")
        long sender_id,
        @NotNull(message = "Il receiver_id è un campo obbligatorio!")
        long receiver_id,
        @NotNull(message = "Lo stato è un campo obbligatorio!")
        String statoNotifica,
        long product_id,
        LocalDate createdAt
) {
}
