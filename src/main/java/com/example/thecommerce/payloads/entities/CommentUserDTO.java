package com.example.thecommerce.payloads.entities;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CommentUserDTO(
        @NotEmpty(message = "Il testo è obbligatorio")
        String testo,
        @NotNull(message="user_id necessario")
        long user_id,
        @NotNull(message="product_id necessario")
        long product_id
) {
}
