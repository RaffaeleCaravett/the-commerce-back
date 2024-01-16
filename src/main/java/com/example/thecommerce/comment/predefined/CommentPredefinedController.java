package com.example.thecommerce.comment.predefined;

import com.example.thecommerce.exception.BadRequestException;
import com.example.thecommerce.payloads.entities.CommentPredefinedDTO;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/predefinedComments")
public class CommentPredefinedController {
    @Autowired
    CommentPredefinedService commentPredefinedService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
public CommentPredefined save(@RequestBody @Validated CommentPredefinedDTO commentPredefinedDTO, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }else{
          return commentPredefinedService.save(commentPredefinedDTO);
        }
    }

    @GetMapping("")
    public List<CommentPredefined> getAll(){
        return commentPredefinedService.getAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
   public CommentPredefined updateById(@PathVariable long id, @RequestBody CommentPredefinedDTO commentPredefinedDTO){
        return commentPredefinedService.updateById(id,commentPredefinedDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
public boolean deleteById(@PathVariable long id){
        return commentPredefinedService.deleteById(id);
    }

@GetMapping("/{userId}")
    public List<CommentPredefined> getAllByUserId(@PathVariable long userId){
        return commentPredefinedService.getAllByUserId(userId);
}
    @GetMapping("/{productId}")
    public List<CommentPredefined> getAllByProductId(@PathVariable long productId){
        return commentPredefinedService.getAllByProductId(productId);
    }



}
