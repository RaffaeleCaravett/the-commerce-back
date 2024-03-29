package com.example.thecommerce.schedaAnagrafica;

import com.example.thecommerce.enums.UserRoles;
import com.example.thecommerce.società.Società;
import com.example.thecommerce.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
public class SchedaAnagrafica {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
private String nome;
private String cognome;
private String email;
private UserRoles role;
private String codiceFiscale;
private String partitaIva;
private String via;
private String indirizzo;
private int numeroCivico;
private long cap;
private double capitaleSociale;
@OneToOne
    @JoinColumn(name = "user_id")
    private User user;
@JsonIgnore
@OneToMany(mappedBy = "schedaAnagrafica", cascade = CascadeType.REMOVE)
    private List<Società> società;
}
