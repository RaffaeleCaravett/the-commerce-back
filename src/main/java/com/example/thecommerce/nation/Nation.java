package com.example.thecommerce.nation;

import com.example.thecommerce.city.City;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Nation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    @JsonIgnore
    @OneToMany(mappedBy = "nation")
    private List<City> cities;
}
