package com.example.thecommerce.ricerca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RicercaRepository extends JpaRepository<Ricerca,Long> {
    List<Ricerca> findByUser_Id(long user_id);
}
