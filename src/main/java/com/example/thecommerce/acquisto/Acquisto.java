package com.example.thecommerce.acquisto;

import com.example.thecommerce.product.Product;
import com.example.thecommerce.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="acquisto")
@Getter
@Setter
public class Acquisto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany
    @JoinTable(name = "acquisto_prodotto",
            joinColumns = @JoinColumn(name = "acquisto_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    List<Product> productList;
    private double totale;
    private LocalDate created_at;
}
