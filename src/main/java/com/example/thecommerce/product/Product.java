package com.example.thecommerce.product;

import com.example.thecommerce.category.Category;
import com.example.thecommerce.enums.TipoProdotto;
import com.example.thecommerce.exception.BadRequestException;
import com.example.thecommerce.società.Società;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Category category;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "società_id")
    private Società societa;
    private String societàName;
    private String immagine;


    public void setPezzi(int pezzi){
        if(this.pezzi>0){
            this.pezzi=pezzi;
        }else{
            throw new BadRequestException("Non ci sono più pezzi per questo articolo");
        }
    }
}
