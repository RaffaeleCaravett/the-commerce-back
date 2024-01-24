package com.example.thecommerce.rating;


import com.example.thecommerce.exception.NotFoundException;
import com.example.thecommerce.payloads.entities.RatingDTO;
import com.example.thecommerce.product.ProductRepository;
import com.example.thecommerce.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class RatingService {

@Autowired
RatingRepository ratingRepository;
@Autowired
UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    public Rating registerRating(RatingDTO body) throws IOException {

        Rating newRating = new Rating();

        newRating.setProduct(productRepository.findById(body.product_id()).get());
        newRating.setUser(userRepository.findById(body.user_id()).get());
        newRating.setRating(body.rating());

        ratingRepository.save(newRating);

        return newRating;
    }
    public Page<Rating> getRatings(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        Page<Rating> pageableRating = ratingRepository.findAll(pageable);

      //  return pageableRating.map(pageRating -> new RatingResponseDTO(pageRating.getArgument().getTitle(), pageRating.getUser().getId(), pageRating.getRating()));
   return pageableRating;
    }

    public Rating findById(long id) throws NotFoundException {
        return ratingRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Rating findByIdAndUpdate(long id, RatingDTO body) throws NotFoundException {
        Rating found = ratingRepository.findById(id).get();
        found.setRating(body.rating());
        found.setProduct(productRepository.findById(body.product_id()).get());
        found.setUser(userRepository.findById(body.user_id()).get());
        return ratingRepository.save(found);
    }

    public void findByIdAndDelete(long id) throws NotFoundException {
        Rating found = this.findById(id);
        ratingRepository.delete(found);
    }
    public List<Rating> findByProductId(long id)throws NotFoundException{

        List<Rating> pageableRating = ratingRepository.findByProductId(id);


        //  return pageableRating.stream()
        //        .map(pageRating -> new RatingResponseDTO(pageRating.getArgument().getTitle(), pageRating.getUser().getId(), pageRating.getRating()))
          //      .collect(Collectors.toList());
      return pageableRating;
    }

    public List<Rating> findByUserId(long userId) throws NotFoundException{

        List<Rating> pageableRating = ratingRepository.findByUserId(userId);

       // return pageableRating.stream()
         //       .map(pageRating -> new RatingResponseDTO(pageRating.getArgument().getTitle(), pageRating.getUser().getId(), pageRating.getRating()))
           //     .collect(Collectors.toList());

   return pageableRating;
    }

    public Page<Rating> findByUserIdPaginated(long userId,int page,int size,String orderBy, String sortDirection) throws NotFoundException{
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Sort sort = Sort.by(direction, orderBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Rating> pageableRating = ratingRepository.findByUserId(userId,pageable);


        //  return pageableRating.map(pageRating -> new RatingResponseDTO(pageRating.getArgument().getTitle(), pageRating.getUser().getId(), pageRating.getRating()));
        return pageableRating;
    }
    public List<Rating> findAll() throws NotFoundException{
        return ratingRepository.findAll();
    }
}
