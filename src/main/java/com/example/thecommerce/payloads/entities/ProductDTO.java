package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProductDTO(
        @NotEmpty(message="Il nome è obbligatorio")
        String nome,
        @NotEmpty(message="Il tipoProdotto è obbligatorio")
        String tipoProdotto,
        @NotNull(message="Il prezzo è obbligatorio")
        double prezzo,
        @NotNull(message="I pezzi è un campo obbligatorio")
        int pezzi,
        @NotNull(message="Il category_id è obbligatorio")
        List<Long> category_id,
        @NotNull(message="La società_id è obbligatoria")
        long societa_id

){
}
