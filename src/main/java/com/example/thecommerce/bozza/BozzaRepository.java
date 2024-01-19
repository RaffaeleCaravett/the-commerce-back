package com.example.thecommerce.bozza;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BozzaRepository extends JpaRepository<Bozza,Long> {
List<Bozza> findByUser_Id(long user_id);
}
