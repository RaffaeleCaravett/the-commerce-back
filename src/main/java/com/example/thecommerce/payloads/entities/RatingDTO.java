package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RatingDTO(
        @NotNull(message = "L'argument_id' è un campo obbligatorio!")
        long argument_id,
        @NotNull(message = "Lo user_id è un campo obbligatorio!")
        long user_id,
        @NotNull(message = "Il rating è un campo obbligatorio!")
        int rating
) {
}
