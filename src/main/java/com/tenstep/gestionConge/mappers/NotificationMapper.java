package com.tenstep.gestionConge.mappers;

import com.tenstep.gestionConge.Models.Notification;
import com.tenstep.gestionConge.dto.NotificationDto;

public class NotificationMapper {

    public static NotificationDto mapToNotificationDto(Notification notification){
        return new NotificationDto(
                notification.getNotification_id(),
                notification.getMessage(),
                notification.getDate()
        );
    }
    public static Notification mapToNotification(NotificationDto notificationDto){
        return new Notification(
                notificationDto.getNotification_id(),
                null,
                notificationDto.getMessage(),
                notificationDto.getDate()
        );
    }
}
