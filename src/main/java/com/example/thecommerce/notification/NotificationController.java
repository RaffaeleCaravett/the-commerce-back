package com.example.thecommerce.notification;

import com.example.thecommerce.enums.StatoNotifica;
import com.example.thecommerce.exception.BadRequestException;
import com.example.thecommerce.exception.NotFoundException;
import com.example.thecommerce.payloads.entities.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('USER')")
    public Page<Notification> getNotification(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size,
                                              @RequestParam(defaultValue = "id") String orderBy){
        return  notificationService.getNotification(page, size, orderBy);
    }


    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public Notification findById(@PathVariable int id)  {
        return notificationService.findById(id);
    }

    @PutMapping("/{id}/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Notification findByIdAndUpdate(@PathVariable int id,@PathVariable long userId, @RequestBody NotificationDTO body) throws NotFoundException {
        if( notificationService.findById(id).getReceiver().getId()==userId&& StatoNotifica.valueOf(body.statoNotifica())== StatoNotifica.NOT_SAW
        ) {
            return  notificationService.findByIdAndUpdate(id);
        }else{
            throw new BadRequestException("Non puoi modificare le notifiche di amicizia degli altri, mascalzone!");
        }
    }

    @DeleteMapping("/{id}/{userId}")
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findByIdAndDelete(@PathVariable long id,@PathVariable long userId) throws NotFoundException {
        if( notificationService.findById(id).getSender().getId()==userId){
            notificationService.findByIdAndDelete(id);
        }else{
            throw new BadRequestException("Non puoi cancellare le richieste di amicizia degli altri, mascalzone!");
        }

    }
    @PostMapping("")
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public String saveNotification(@RequestBody @Validated NotificationDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return  notificationService.registerNotification(body);
                //  RatingResponseDTO ratingResponseDTO = new RatingResponseDTO(rating.getArgument().getTitle(),rating.getUser().getId(),rating.getRating());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping(value = "/sender/{senderId}")
    @PreAuthorize("hasAuthority('USER')")
    public List<Notification> findBySenderId(@PathVariable long senderId)  {
        return  notificationService.findBySenderId(senderId);
    }
    @GetMapping(value = "/receiver/{receiverId}")
    @PreAuthorize("hasAuthority('USER')")
    public List<Notification> findByReceiverId(@PathVariable long receiverId)  {
        return  notificationService.findByReceiverId(receiverId);
    }

}