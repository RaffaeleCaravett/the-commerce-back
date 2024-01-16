package com.example.thecommerce.notification;

import com.example.thecommerce.enums.StatoNotifica;
import com.example.thecommerce.exception.BadRequestException;
import com.example.thecommerce.exception.NotFoundException;
import com.example.thecommerce.payloads.entities.NotificationDTO;
import com.example.thecommerce.payloads.entities.NotificationResponseDTO;
import com.example.thecommerce.product.ProductRepository;
import com.example.thecommerce.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;



    public String registerNotification(NotificationDTO body) throws IOException {

        Notification newNotification = new Notification();
        newNotification.setSender(userRepository.findById(body.sender_id()).get());
        newNotification.setReceiver(userRepository.findById(body.receiver_id()).get());
        newNotification.setStatoNotifica(StatoNotifica.NOT_SAW);
        newNotification.setCreatedAt(LocalDate.now());
        if(body.product_id()!=0){
            newNotification.setProduct(productRepository.findById(body.product_id()).get());
        }

        AtomicReference<String> s= new AtomicReference<>("");

        if(body.receiver_id()!= body.sender_id()) {
            notificationRepository.save(newNotification);
            s.set("created");
        }else{
            throw new BadRequestException("Il mittente ed il ricevente non possono avere lo stesso id");
        }

        return s.get();

    }
    private NotificationResponseDTO mapToFriendshipResponseDTO(Notification notification) {
        // Assuming LikeResponseDTO has appropriate fields
        NotificationResponseDTO notificationResponseDTO = new NotificationResponseDTO(
                notification.getId(),notification.getSender().getId(),notification.getReceiver().getId(),
                String.valueOf(notification.getStatoNotifica()),notification.getProduct().getId(),
                notification.getCreatedAt()
        );
        return notificationResponseDTO;
    }
    public Page<Notification> getNotification(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return notificationRepository.findAll(pageable);
    }

    public Notification findById(long id) throws NotFoundException {
        return notificationRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Notification findByIdAndUpdate(long id) throws NotFoundException {
        Notification found = notificationRepository.findById(id).get();
        found.setStatoNotifica(StatoNotifica.SAW);
        return notificationRepository.save(found);
    }

    public void findByIdAndDelete(long id) throws NotFoundException {
        Notification found = this.findById(id);
        notificationRepository.delete(found);
    }
    public List<Notification> findBySenderId(long senderId)throws NotFoundException{
        List<Notification> pageableNotification = notificationRepository.findAllBySender_Id(senderId);

        //  return pageableRating.stream()
        //        .map(pageRating -> new RatingResponseDTO(pageRating.getArgument().getTitle(), pageRating.getUser().getId(), pageRating.getRating()))
        //      .collect(Collectors.toList());
        return pageableNotification;
    }

    public List<Notification> findByReceiverId(long receiverId) throws NotFoundException{
        List<Notification> pageableNotification = notificationRepository.findAllByReceiver_Id(receiverId);

        // return pageableRating.stream()
        //       .map(pageRating -> new RatingResponseDTO(pageRating.getArgument().getTitle(), pageRating.getUser().getId(), pageRating.getRating()))
        //     .collect(Collectors.toList());

        return pageableNotification;
    }
    public List<Notification> findAll() throws NotFoundException{
        return notificationRepository.findAll();
    }
}
