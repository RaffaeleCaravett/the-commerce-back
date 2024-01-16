package com.example.thecommerce.comment.user;

import com.example.thecommerce.comment.Comment;
import com.example.thecommerce.product.Product;
import com.example.thecommerce.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="User_comments")
@Getter
@Setter
public class CommentUser extends Comment {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    public CommentUser(String testo, LocalDate created_at,User user,Product product) {
        super(testo, created_at);
        this.user=user;
        this.product=product;
    }
}
