package com.tenstep.gestionConge.Controllers;

import com.tenstep.gestionConge.Models.Notification;
import com.tenstep.gestionConge.Services.NotificationService;
import com.tenstep.gestionConge.dto.NotificationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
 public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService=notificationService;
    }
    @PostMapping
    public ResponseEntity<NotificationDto> createNotification(@RequestBody NotificationDto notificationDto){
        NotificationDto savedNotif = notificationService.createNotification(notificationDto);
        return new ResponseEntity<>(savedNotif, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable String id){
        notificationService.deleteNotification(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
