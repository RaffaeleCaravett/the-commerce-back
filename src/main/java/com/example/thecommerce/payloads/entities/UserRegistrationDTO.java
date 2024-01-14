package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserRegistrationDTO(
        long id,
        @NotNull(message = "L'età è un campo obbligatorio!")
        int età,
        @NotEmpty(message = "L'email è un campo obbligatorio!")
        String email,
        @NotEmpty(message = "La password è un campo obbligatorio!")
        String password,
        @NotEmpty(message = "Il nome è un campo obbligatorio!")
        String nome,
        @NotEmpty(message = "Il cognome è un campo obbligatorio!")
        String cognome,
        @NotNull(message = "Il continente è un campo obbligatorio!")
        Long continente,
        @NotNull(message = "La nazione è un campo obbligatorio!")
        Long nazione,
        String img_profilo,
        String description
) {
}
