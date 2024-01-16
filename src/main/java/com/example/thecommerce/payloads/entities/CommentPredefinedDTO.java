package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CommentPredefinedDTO(
        @NotEmpty(message = "Il testo è obbligatorio")
        String testo,
        List<Long> user_id_list,
        List<Long> product_id_list
) {
}
