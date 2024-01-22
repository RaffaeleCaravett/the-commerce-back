package com.example.thecommerce.carrello;


import com.example.thecommerce.product.Product;
import com.example.thecommerce.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="carrello")
@Getter
@Setter
public class Carrello {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
private double totale;
@ManyToMany
    @JoinTable(name = "carrello_prodotto",
            joinColumns = @JoinColumn(name = "carrello_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
@OneToOne
@JoinColumn(name = "user_id")
    private User user;
private LocalDate created_at;
}
