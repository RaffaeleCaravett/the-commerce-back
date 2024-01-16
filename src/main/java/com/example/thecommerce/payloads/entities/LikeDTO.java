package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LikeDTO(
        @NotNull(message = "Il comment_id' è un campo obbligatorio!")
        long product_id,
        @NotNull(message = "Lo user_id è un campo obbligatorio!")
        long user_id
) {
}
