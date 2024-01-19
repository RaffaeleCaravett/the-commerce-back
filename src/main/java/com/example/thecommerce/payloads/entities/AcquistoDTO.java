package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AcquistoDTO(
        @NotNull(message = "User_id necessario.")
        long user_id,
        @NotNull(message="Uno o più prodotti necessari")
        List<Long> product_id
) {
}
