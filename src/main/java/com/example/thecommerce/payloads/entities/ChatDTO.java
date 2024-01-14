package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotNull;

public record ChatDTO(

        @NotNull(message="lo user_id1 è necessario")
        long user_id1,
        @NotNull(message="lo user_id2 è necessario")
        long user_id2,
        long message_id

) {
}
