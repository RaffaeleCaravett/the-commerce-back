package com.example.thecommerce.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByCategory_Id(long id, Pageable pageable);
    Page<Product> findBySocieta_Id(long id,Pageable pageable);
    List<Product> findBySocieta_Id(long id);

}
