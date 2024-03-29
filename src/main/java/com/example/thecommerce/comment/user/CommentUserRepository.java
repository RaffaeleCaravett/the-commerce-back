package com.example.thecommerce.comment.user;

import com.example.thecommerce.comment.user.CommentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentUserRepository extends JpaRepository<CommentUser,Long> {
    List<CommentUser> findByUser_Id(long id);
    List<CommentUser> findByProduct_Id(long id);
}
