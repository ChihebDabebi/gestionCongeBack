package com.tenstep.gestionConge.Services;

import com.tenstep.gestionConge.dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    NotificationDto createNotification(NotificationDto notificationDto);

    void deleteNotification(String notification_id);
}