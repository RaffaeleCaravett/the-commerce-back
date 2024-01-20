package com.example.thecommerce.rating;


import com.example.thecommerce.exception.BadRequestException;
import com.example.thecommerce.exception.NotFoundException;
import com.example.thecommerce.payloads.entities.RatingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('UTENTE')")
    public Page<Rating> getRatings(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size,
                                              @RequestParam(defaultValue = "id") String orderBy){
        return ratingService.getRatings(page, size, orderBy);
    }
    @GetMapping("/user/paginatedByRating/{userId}")
    @PreAuthorize("hasAuthority('UTENTE')")
    public Page<Rating> findByUserIdPaginated(@PathVariable long userId, @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam(defaultValue = "rating") String orderBy,
                                              @RequestParam(defaultValue = "asc") String sortDirection) {
        return ratingService.findByUserIdPaginated(userId, page,size,orderBy,sortDirection);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('UTENTE')")
    public Rating findById(@PathVariable int id)  {
        return ratingService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','UTENTE')")
    public Rating findByIdAndUpdate(@PathVariable int id, @RequestBody RatingDTO body) throws NotFoundException {
        return ratingService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('UTENTE')")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findByIdAndDelete(@PathVariable long id) throws NotFoundException {
        ratingService.findByIdAndDelete(id);
    }
    @PostMapping("")
    @PreAuthorize("hasAuthority('UTENTE')")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Rating saveRating(@RequestBody @Validated RatingDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                List<Rating> ratingList = ratingService.findAll();
                ratingList.forEach(rating ->{
                    if(rating.getProduct().getId()== body.product_id()&&rating.getUser().getId()== body.user_id()){
                       this.findByIdAndDelete(rating.getId());
                    }
                });
                Rating rating = ratingService.registerRating(body);
              //  RatingResponseDTO ratingResponseDTO = new RatingResponseDTO(rating.getArgument().getTitle(),rating.getUser().getId(),rating.getRating());
                return  rating;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("/product/{id}")
    @PreAuthorize("hasAuthority('UTENTE')")
    public Page<Rating> findByArgumentName(@PathVariable long id)  {
        return ratingService.findByProductId(id,1,5,"id");
    }
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('UTENTE')")
    public List<Rating> findByUserId(@PathVariable long userId)  {
        return ratingService.findByUserId(userId);
    }


}
