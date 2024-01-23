package com.example.thecommerce.like;

import com.example.thecommerce.exception.NotFoundException;
import com.example.thecommerce.payloads.entities.LikeDTO;
import com.example.thecommerce.payloads.entities.LikeResponseDTO;
import com.example.thecommerce.product.ProductRepository;
import com.example.thecommerce.user.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class LikeService {
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;


    public String registerLike(LikeDTO body) {
Like like = likeRepository.findByProduct_IdAndUser_Id(body.product_id(), body.user_id());
if(like ==null){
    Like like1= new Like();
    like1.setProduct(productRepository.findById(body.product_id()).get());
    like1.setUser(userRepository.findById(body.user_id()).get());
    like1.setCreated_at(LocalDate.now());
     likeRepository.save(like1);
     return "Prodotto aggiunto ai preferiti";
}else {
    this.findByIdAndDelete(like.getId());
    return "Prodotto rimosso dai preferiti.";
}
    }

    private LikeResponseDTO mapToLikeResponseDTO(Like like) {
        // Assuming LikeResponseDTO has appropriate fields
        LikeResponseDTO likeDTO = new LikeResponseDTO(
                like.getId(),like.getProduct().getId(),like.getUser().getId(),like.getCreated_at()
        );
        return likeDTO;
    }
    public List<LikeResponseDTO> getLikes(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        Page<Like> pageableLike = likeRepository.findAll(pageable);

        //  return pageableRating.map(pageRating -> new RatingResponseDTO(pageRating.getArgument().getTitle(), pageRating.getUser().getId(), pageRating.getRating()));
        List<LikeResponseDTO> likeDTOs = pageableLike
                .map(this::mapToLikeResponseDTO)
                .getContent();

        return likeDTOs;
    }

    public Like findById(long id) throws NotFoundException {
        return likeRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Like findByIdAndUpdate(long id, LikeDTO body) throws NotFoundException {
        Like found = likeRepository.findById(id).get();
        found.setProduct(productRepository.findById(body.product_id()).get());
        found.setUser(userRepository.findById(body.user_id()).get());
        found.setUser(userRepository.findById(body.user_id()).get());
        return likeRepository.save(found);
    }

    public void findByIdAndDelete(long id) throws NotFoundException {
        Like found = this.findById(id);
        likeRepository.delete(found);
    }
    public List<Like> findByProductId(long productId)throws NotFoundException{
        List<Like> pageableLike = likeRepository.findByProduct_Id(productId);

        //  return pageableRating.stream()
        //        .map(pageRating -> new RatingResponseDTO(pageRating.getArgument().getTitle(), pageRating.getUser().getId(), pageRating.getRating()))
        //      .collect(Collectors.toList());
        return pageableLike;
    }

    public List<Like> findByUserId(long userId) throws NotFoundException{
        List<Like> pageableLike = likeRepository.findByUser_Id(userId);

        // return pageableRating.stream()
        //       .map(pageRating -> new RatingResponseDTO(pageRating.getArgument().getTitle(), pageRating.getUser().getId(), pageRating.getRating()))
        //     .collect(Collectors.toList());

        return pageableLike;
    }
    public List<Like> findAll() throws NotFoundException{
        return likeRepository.findAll();
    }

    public Like findByProductIdAndUserId(Long productId, Long userId) {
        return likeRepository.findByProduct_IdAndUser_Id(productId, userId);
    }
}
