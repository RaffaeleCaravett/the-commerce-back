package com.example.thecommerce.comment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="Comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Comment {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
private String testo;
private LocalDate created_at;

    public Comment(String testo, LocalDate created_at) {
        this.testo = testo;
        this.created_at = created_at;
    }
}
