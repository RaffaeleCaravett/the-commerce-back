package com.example.thecommerce.user;


import com.example.thecommerce.city.City;
import com.example.thecommerce.enums.UserRoles;
import com.example.thecommerce.nation.Nation;
import com.example.thecommerce.rating.Rating;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "età")
    private Integer età;

    @ManyToOne
    @JoinColumn(name = "nation_id")
    private Nation nazione;
    @ManyToOne
    @JoinColumn(name = "nation_id")
    private City citta;

    @Column(name = "nomeCompleto", length = 255)
    private String nomeCompleto;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRoles role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Like> likes;
    @JsonIgnore
    @OneToMany(mappedBy = "receiver")
    private List<Notification> notifications;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", età=" + età +
                ", nazione=" + nazione +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", ratings=" + ratings +
                ", comments=" + comments +
                ", likes=" + likes +
                ", notifications=" + notifications +
                '}';
    }
}

