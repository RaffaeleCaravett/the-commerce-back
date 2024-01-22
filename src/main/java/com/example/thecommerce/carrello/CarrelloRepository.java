package com.example.thecommerce.carrello;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrelloRepository extends JpaRepository<Carrello,Long> {
    Carrello getByUser_Id(long user_id);
}
