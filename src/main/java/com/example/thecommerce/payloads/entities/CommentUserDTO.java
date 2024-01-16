package com.example.thecommerce.payloads.entities;


import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CommentUserDTO(
        @NotEmpty(message = "Il testo è obbligatorio")
        String testo,
        long user_id,
        long product_id
) {
}
