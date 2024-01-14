package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record MessageDTO(

        @NotNull(message = "il sender_id è obbligatorio")
        long sender_id,
        @NotNull(message="il receiver_id è obbligatorio")
        long receiver_id,
        @NotEmpty(message = "il text deve contenere qualcosa")
        String text,
        @NotNull(message= "il chat id deve essere valorizzato")
        long chat_id
        ) {
}
