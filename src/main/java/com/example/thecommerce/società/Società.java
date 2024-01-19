package com.example.thecommerce.società;

import com.example.thecommerce.product.Product;
import com.example.thecommerce.schedaAnagrafica.SchedaAnagrafica;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Società {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    @ManyToOne
    @JoinColumn(name="scheda_anagrafica_id")
    private SchedaAnagrafica schedaAnagrafica;
    @OneToMany(mappedBy = "societa", cascade = CascadeType.REMOVE)
    private List<Product> products;

}
