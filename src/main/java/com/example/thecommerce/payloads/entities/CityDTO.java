package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CityDTO(
        @NotEmpty(message = "Il nome è obbligatorio")
        String nome,
        @NotNull(message = "Il nation_id è obbligatorio")
        long nation_id
) {
}
