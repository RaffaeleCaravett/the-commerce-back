package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SocietàDTO (
        @NotEmpty(message = "Il nome è obbligatorio")
        String nome,
        @NotNull(message = "L'id della scheda anagrafica è obbligatorio")
        long scheda_anagrafica_id ){
}

