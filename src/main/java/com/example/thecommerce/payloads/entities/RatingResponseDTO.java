package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotNull;

public record RatingResponseDTO (
        String argument_name,
        long user_id,
        int rating
){
}
