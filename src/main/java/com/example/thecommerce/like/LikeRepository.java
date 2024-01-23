package com.example.thecommerce.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {

    List<Like> findByProduct_Id(long productId);

    List<Like> findByUser_Id(long userId);
    Like findByProduct_IdAndUser_Id(Long productId, Long userId);


}
