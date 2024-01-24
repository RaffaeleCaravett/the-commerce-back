package com.example.thecommerce.comment.predefined;

import com.example.thecommerce.exception.BadRequestException;
import com.example.thecommerce.exception.NotFoundException;
import com.example.thecommerce.payloads.entities.CommentPredefinedDTO;
import com.example.thecommerce.product.ProductRepository;
import com.example.thecommerce.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentPredefinedService {
    @Autowired
    CommentPredefinedRepository commentPredefinedRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    public CommentPredefined save(CommentPredefinedDTO commentPredefinedDTO){
        CommentPredefined commentPredefined= new CommentPredefined();
        commentPredefined.setTesto(commentPredefinedDTO.testo());
        commentPredefined.setCreated_at(LocalDate.now());

        commentPredefined.getUsers().add(userRepository.findById(commentPredefinedDTO.user_id()).get());
        commentPredefined.getProducts().add(productRepository.findById(commentPredefinedDTO.product_id()).get());
        return commentPredefinedRepository.save(commentPredefined);
    }

    public List<CommentPredefined> getAll(){
        return commentPredefinedRepository.findAll();
    }

    public List<CommentPredefined> getAllByUserId(long id){
        return commentPredefinedRepository.findByUsers_Id(id);
    }
    public List<CommentPredefined> getAllByProductId(long id){
        return commentPredefinedRepository.findByProducts_Id(id);
    }

    public CommentPredefined updateById(long id, CommentPredefinedDTO commentPredefinedDTO){
        CommentPredefined commentPredefined= commentPredefinedRepository.findById(id).orElseThrow(() -> new NotFoundException("Commento predefinito non trovato"));

commentPredefined.setTesto(commentPredefinedDTO.testo());


        return commentPredefinedRepository.save(commentPredefined);
    }

    public boolean deleteById(long id){
        try {
            commentPredefinedRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
