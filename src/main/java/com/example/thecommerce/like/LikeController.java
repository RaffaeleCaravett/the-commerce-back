package com.example.thecommerce.like;

import com.example.thecommerce.exception.BadRequestException;
import com.example.thecommerce.exception.NotFoundException;
import com.example.thecommerce.payloads.entities.LikeDTO;
import com.example.thecommerce.payloads.entities.LikeResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('VENDITORE','UTENTE')")
    public List<LikeResponseDTO> getLike(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "id") String orderBy){
        return likeService.getLikes(page, size, orderBy);
    }


    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('VENDITORE','UTENTE')")
    public Like findById(@PathVariable int id)  {
        return likeService.findById(id);
    }

    @PutMapping("/{id}/{userId}")
    @PreAuthorize("hasAnyAuthority('VENDITORE','UTENTE')")
    public Like findByIdAndUpdate(@PathVariable int id,@PathVariable long userId, @RequestBody LikeDTO body) throws NotFoundException {
        if(likeService.findById(id).getUser().getId()==userId) {
            return likeService.findByIdAndUpdate(id, body);
        }else{
            throw new BadRequestException("Non puoi modificare i like degli altri, mascalzone!");

        }
    }

    @DeleteMapping("/{id}/{userId}")
    @PreAuthorize("hasAnyAuthority('VENDITORE','UTENTE')")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findByIdAndDelete(@PathVariable long id,@PathVariable long userId) throws NotFoundException {
        if(likeService.findById(id).getUser().getId()==userId){
            likeService.findByIdAndDelete(id);
        }else{
            throw new BadRequestException("Non puoi cancellare i likes degli altri, mascalzone!");
        }

    }
    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('VENDITORE','UTENTE')")
    public String saveLike(@RequestBody @Validated LikeDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                //Like l =

                //  RatingResponseDTO ratingResponseDTO = new RatingResponseDTO(rating.getArgument().getTitle(),rating.getUser().getId(),rating.getRating());
                return  likeService.registerLike(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("/product/{productId}")
    @PreAuthorize("hasAnyAuthority('VENDITORE','UTENTE')")
    public List<Like> findByProductId(@PathVariable long productId)  {
        return likeService.findByProductId(productId);
    }
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyAuthority('VENDITORE','UTENTE')")
    public List<Like> findByUserId(@PathVariable long userId)  {
        return likeService.findByUserId(userId);
    }
    @GetMapping("/productUser")
    @PreAuthorize("hasAnyAuthority('VENDITORE','UTENTE')")
    public Like findByProductIdAndUserId(
            @RequestParam Long productId,
            @RequestParam Long userId) {
        return likeService.findByProductIdAndUserId(productId, userId);
    }
}
