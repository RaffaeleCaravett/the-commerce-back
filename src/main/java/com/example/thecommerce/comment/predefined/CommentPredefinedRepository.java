package com.example.thecommerce.comment.predefined;

import com.example.thecommerce.comment.predefined.CommentPredefined;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentPredefinedRepository extends JpaRepository<CommentPredefined,Long> {
    List<CommentPredefined> findByUser_Id(long id);
    List<CommentPredefined> findByProduct_Id(long id);
}
