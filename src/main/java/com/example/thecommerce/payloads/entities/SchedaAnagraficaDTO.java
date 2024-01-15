package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SchedaAnagraficaDTO(
        @NotEmpty(message = "Il nome è un campo obbligatorio")
        String nome,

        @NotEmpty(message = "Il cognome è un campo obbligatorio")
        String cognome,

        @NotEmpty(message = "L'email è un campo obbligatorio")
        String email,

        @NotEmpty(message = "Il ruolo è un campo obbligatorio")
        String role,

        String codiceFiscale,
        String partitaIva,
        @NotEmpty(message = "La via è un campo obbligatorio")
        String via,

        @NotEmpty(message = "L'indirizzo è un campo obbligatorio")
        String indirizzo,
        @NotNull(message = "Il numero civico è un campo obbligatorio")

        int numeroCivico,
        @NotNull(message = "Il cap è un campo obbligatorio")

        long cap,
        double capitaleSociale,
        @NotNull(message = "Lo user_id è obbligatorio")
        long user_id
) {
}
