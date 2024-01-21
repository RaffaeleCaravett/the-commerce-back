package com.example.thecommerce.product;

import com.example.thecommerce.category.Category;
import com.example.thecommerce.enums.TipoProdotto;
import com.example.thecommerce.società.Società;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "/products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoProdotto tipoProdotto;
    private double prezzo;
    private int pezzi;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "società_id")
    private Società societa;
    private String immagine;
}
