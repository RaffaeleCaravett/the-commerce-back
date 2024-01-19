package com.example.thecommerce.bozza;

import com.example.thecommerce.product.Product;
import com.example.thecommerce.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="bozze")
@Getter
@Setter
public class Bozza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToMany
    @JoinTable(name = "bozza_articolo",
            joinColumns = @JoinColumn(name = "bozza_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
    private LocalDate created_at;
}
