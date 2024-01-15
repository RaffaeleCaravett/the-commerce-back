package com.example.thecommerce.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByCategory_Id(long id, Pageable pageable);
    Page<Product> findBySocietà_Id(long id,Pageable pageable);
}