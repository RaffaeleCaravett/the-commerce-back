package com.example.thecommerce.product;

import com.example.thecommerce.category.Category;
import com.example.thecommerce.enums.TipoProdotto;
import com.example.thecommerce.società.Società;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
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
    @ManyToMany
    @JoinTable(name="product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Category category;
    @ManyToOne
    @JoinColumn(name = "società_id")
    private Società società;
    private String immagine;
}
