package com.tenstep.gestionConge.utils;

import com.tenstep.gestionConge.Services.impl.EmailSenderService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
public class EmailNotificationTask implements Runnable {

    private final EmailSenderService senderService;
    private final String email;



    @Override
    public void run() {
        try {
            NotificationMail.sendAccepted(senderService, email);
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }
    }
}