package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CommentPredefinedDTO(
        @NotEmpty(message = "Il testo è obbligatorio")
        String testo,
        @NotNull(message="user_id obbligatorio")
        long user_id,
        @NotNull(message="product_id obbligatorio")
        long product_id
) {
}
