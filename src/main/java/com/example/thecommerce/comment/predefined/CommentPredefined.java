package com.example.thecommerce.comment.predefined;

import com.example.thecommerce.comment.Comment;
import com.example.thecommerce.product.Product;
import com.example.thecommerce.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Predefined_comments")
@Getter
@Setter
@NoArgsConstructor
public class CommentPredefined extends Comment {
  @ManyToMany
  @JoinTable(name = "predefined_user",
          joinColumns = @JoinColumn(name = "predefined_id"),
          inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> users;
    @ManyToMany
    @JoinTable(name = "predefined_products",
            joinColumns = @JoinColumn(name = "predefined_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public CommentPredefined(String testo, LocalDate created_at,List<User> users ,List<Product> products) {
        super(testo, created_at);
    this.users=users;
    this.products=products;
    }
}
