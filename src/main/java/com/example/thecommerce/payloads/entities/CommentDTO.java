package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CommentDTO(
        @NotNull(message = "L'argument_id' è un campo obbligatorio!")
        long argument_id,
        @NotNull(message = "Lo user_id è un campo obbligatorio!")
        long user_id,
        @NotEmpty(message = "Il testo è un campo obbligatorio!")
        String testo
) {
}
