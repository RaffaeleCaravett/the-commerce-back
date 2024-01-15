package com.example.thecommerce.city;

import com.example.thecommerce.nation.Nation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="city")
@Getter
@Setter
public class City {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String nome;
    @ManyToMany
    @JoinTable(name = "nation_city",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "nation_id"))
    private List<Nation> nationList;

    public City(String nome) {
        this.nome = nome;
    }
}
