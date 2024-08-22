package com.tenstep.gestionConge.utils;

import com.tenstep.gestionConge.Services.impl.EmailSenderService;
import org.springframework.stereotype.Component;


public class EmailNotificationTaskRejected implements Runnable {

    private final EmailSenderService senderService;
    private final String email;

    public EmailNotificationTaskRejected(EmailSenderService senderService, String email) {
        this.senderService = senderService;
        this.email = email;
    }

    @Override
    public void run() {
        try {
            NotificationMail.sendRefused(senderService, email);
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }
    }
}