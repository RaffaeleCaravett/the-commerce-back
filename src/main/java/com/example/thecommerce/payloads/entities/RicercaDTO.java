package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RicercaDTO(
        @NotNull(message = "User id necessario")
        long user_id,
        @NotEmpty(message = "Manca il campo ricerca")
        String ricerca
) {
}
