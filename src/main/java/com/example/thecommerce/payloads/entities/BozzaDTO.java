package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record BozzaDTO(
        @NotNull(message = "User id necessario")
        long user_id,
        @NotNull(message = "Product id necessario")
        List<Long> product_id
) {
}
