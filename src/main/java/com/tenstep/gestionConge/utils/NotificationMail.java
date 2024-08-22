package com.tenstep.gestionConge.utils;

import com.tenstep.gestionConge.Services.impl.EmailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;

public class NotificationMail {

    public static void sendAccepted(EmailSenderService senderService , String toEmail) throws MessagingException {
        senderService.sendEmail(toEmail,"demande de congé","Bonjour,\n\n" +
                "Nous avons le plaisir de vous informer que votre demande de congé a été acceptée.\n" +
                "Profitez bien de votre temps libre et n'hésitez pas à nous contacter si vous avez des questions.\n\n" +
                "Cordialement,\n" +
                "L'équipe RH");

    }
    public static void sendRefused(EmailSenderService senderService, String toEmail) throws MessagingException {
        senderService.sendEmail(toEmail,"demande de congé","Bonjour,\n\n" +
                "Nous regrettons de vous informer que votre demande de congé a été refusée.\n" +
                "Pour plus de détails, veuillez contacter le service des ressources humaines.\n\n" +
                "Cordialement,\n" +
                "L'équipe RH");

    }
}
