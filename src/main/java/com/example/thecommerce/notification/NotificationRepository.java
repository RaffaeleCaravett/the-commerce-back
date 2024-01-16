package com.example.thecommerce.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findAllByReceiver_Id(long receiverId);
    List<Notification> findAllBySender_Id(long receiverId);

}
