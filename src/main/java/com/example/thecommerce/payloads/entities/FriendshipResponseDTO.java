package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FriendshipResponseDTO(

        @NotNull(message = "L'id non può essere null")
        long id,
        @NotNull(message = "Il sender_id' è un campo obbligatorio!")
        long sender_id,
        @NotNull(message = "Il receiver_id è un campo obbligatorio!")
        long receiver_id,
        String friendshipState,
        LocalDate createdAt
) {
}
