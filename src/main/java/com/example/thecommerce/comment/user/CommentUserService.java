package com.example.thecommerce.comment.user;

import com.example.thecommerce.comment.predefined.CommentPredefined;
import com.example.thecommerce.comment.predefined.CommentPredefinedRepository;
import com.example.thecommerce.exception.NotFoundException;
import com.example.thecommerce.payloads.entities.CommentPredefinedDTO;
import com.example.thecommerce.payloads.entities.CommentUserDTO;
import com.example.thecommerce.product.ProductRepository;
import com.example.thecommerce.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentUserService {
    @Autowired
    CommentUserRepository commentUserRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    public CommentUser save(CommentUserDTO commentUserDTO){
        CommentUser commentUser= new CommentUser();
        commentUser.setTesto(commentUserDTO.testo());
        commentUser.setCreated_at(LocalDate.now());
        return commentUserRepository.save(commentUser);
    }

    public List<CommentUser> getAll(){
        return commentUserRepository.findAll();
    }

    public List<CommentUser> getAllByUserId(long id){
        return commentUserRepository.findByUser_Id(id);
    }
    public List<CommentUser> getAllByProductId(long id){
        return commentUserRepository.findByProduct_Id(id);
    }

    public CommentUser updateById(long id, CommentUserDTO commentUserDTO){
        CommentUser commentUser= commentUserRepository.findById(id).orElseThrow(() -> new NotFoundException("Commento non trovato"));

        commentUser.setTesto(commentUserDTO.testo());

                commentUser.setUser(userRepository.findById(commentUserDTO.user_id()).get());
                commentUser.setProduct(productRepository.findById(commentUserDTO.product_id()).get());


        return commentUserRepository.save(commentUser);
    }

    public boolean deleteById(long id){
        try {
            commentUserRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}