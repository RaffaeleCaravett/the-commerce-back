package com.example.thecommerce.notification;

import com.example.thecommerce.enums.StatoNotifica;
import com.example.thecommerce.product.Product;
import com.example.thecommerce.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "sender_user_id",referencedColumnName = "id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiver_user_id",referencedColumnName = "id")
    private User receiver;
    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;
    @Enumerated(EnumType.STRING)
    private StatoNotifica statoNotifica;
    private LocalDate createdAt;
}
