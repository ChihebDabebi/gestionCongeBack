package com.tenstep.gestionConge.Services.impl;

import com.tenstep.gestionConge.Models.Notification;
import com.tenstep.gestionConge.Repositories.NotificationRepository;
import com.tenstep.gestionConge.Services.NotificationService;
import com.tenstep.gestionConge.dto.NotificationDto;
import com.tenstep.gestionConge.mappers.NotificationMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository){
        this.notificationRepository=notificationRepository;
    }


    @Override
    public void deleteNotification(String notification_id) {
        Notification notification = notificationRepository.findById(notification_id).orElseThrow(()-> new RuntimeException("Notification nexiste pas"));
        notificationRepository.deleteById(notification_id);
    }
}
