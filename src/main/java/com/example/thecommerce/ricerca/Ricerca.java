package com.example.thecommerce.ricerca;

import com.example.thecommerce.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ricerche")
@Getter
@Setter
public class Ricerca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String testo;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
