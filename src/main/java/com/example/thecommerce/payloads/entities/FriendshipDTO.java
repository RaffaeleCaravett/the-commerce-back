package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotNull;

public record FriendshipDTO(
        @NotNull(message = "Il sender_id' è un campo obbligatorio!")
        long sender_id,
        @NotNull(message = "Il receiver_id è un campo obbligatorio!")
        long receiver_id,
        String friendshipState
) {
}
