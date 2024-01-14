package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record
LikeResponseDTO (
        @NotNull(message = "L'id' è un campo obbligatorio!")
        long id ,
        @NotNull(message = "Il comment_id' è un campo obbligatorio!")
        long comment_id,
        @NotNull(message = "Lo user_id è un campo obbligatorio!")
        long user_id,
        LocalDate createdAt
){


}
