package com.example.thecommerce.city;

import com.example.thecommerce.nation.Nation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="city")
@Getter
@Setter
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "nation_id")
    private Nation nation;

    public City(String nome) {
        this.nome = nome;
    }
}
