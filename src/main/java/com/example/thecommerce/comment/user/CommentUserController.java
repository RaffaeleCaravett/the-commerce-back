package com.example.thecommerce.comment.user;

import com.example.thecommerce.comment.predefined.CommentPredefined;
import com.example.thecommerce.comment.predefined.CommentPredefinedService;
import com.example.thecommerce.exception.BadRequestException;
import com.example.thecommerce.payloads.entities.CommentPredefinedDTO;
import com.example.thecommerce.payloads.entities.CommentUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commentUser")
public class CommentUserController {
    @Autowired
    CommentUserService commentUserService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommentUser save(@RequestBody @Validated CommentUserDTO commentUserDTO, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }else{
            return commentUserService.save(commentUserDTO);
        }
    }

    @GetMapping("")
    public List<CommentUser> getAll(){
        return commentUserService.getAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommentUser updateById(@PathVariable long id, @RequestBody CommentUserDTO commentUserDTO){
        return commentUserService.updateById(id,commentUserDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean deleteById(@PathVariable long id){
        return commentUserService.deleteById(id);
    }

    @GetMapping("/{userId}")
    public List<CommentUser> getAllByUserId(@PathVariable long userId){
        return commentUserService.getAllByUserId(userId);
    }
    @GetMapping("/{productId}")
    public List<CommentUser> getAllByProductId(@PathVariable long productId){
        return commentUserService.getAllByProductId(productId);
    }

}
