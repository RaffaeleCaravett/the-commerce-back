package com.example.thecommerce.like;

import com.example.thecommerce.exception.NotFoundException;
import com.example.thecommerce.payloads.entities.LikeDTO;
import com.example.thecommerce.payloads.entities.LikeResponseDTO;
import com.example.thecommerce.product.ProductRepository;
import com.example.thecommerce.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

import java.io.IOException;
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


    public String registerLike(LikeDTO body) throws IOException {

        AtomicReference<String> result = new AtomicReference<>("");

        Like newLike = new Like();
        newLike.setProduct(productRepository.findById(body.product_id()).get());
        newLike.setUser(userRepository.findById(body.user_id()).get());

        List<Like> likes = this.findAll();

        if (likes.isEmpty()) {
            likeRepository.save(newLike);
            result.set("created");
        } else {
            for (Like like : likes) {
                if (like.getProduct().getId() == body.product_id() && like.getUser().getId() == body.user_id()) {
                    result.set("deleted");
                    this.findByIdAndDelete(like.getId());
                }
            }
        }
        if(!Objects.equals(result.get(), "deleted")){
            likeRepository.save(newLike);
        }
        return result.get();
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
