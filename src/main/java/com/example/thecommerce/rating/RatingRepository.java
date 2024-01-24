package com.example.thecommerce.rating;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {

    List<Rating> findByUserId(long userId);

    Page<Rating> findByUserId(long userId,Pageable pageable);
    List<Rating> findByProductId(long productId);

}
