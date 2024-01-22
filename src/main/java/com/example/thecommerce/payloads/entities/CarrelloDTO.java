package com.example.thecommerce.payloads.entities;

import jakarta.validation.constraints.NotNull;
import org.aspectj.weaver.ast.Not;

import java.util.List;

public record CarrelloDTO(
        @NotNull
        List<Long> products_id,
        @NotNull
        long user_id
) {
}
