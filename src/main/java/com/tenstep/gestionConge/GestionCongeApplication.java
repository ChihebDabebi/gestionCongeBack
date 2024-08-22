package com.tenstep.gestionConge;

import com.tenstep.gestionConge.Services.impl.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication

public class GestionCongeApplication {
	@Autowired
	private EmailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(GestionCongeApplication.class, args);
	}

	//@EventListener(ApplicationReadyEvent.class)
	//public void sendMail(){
	//	senderService.sendEmail("dabebi.chiheb@esprit.tn","notif","congé accepté enjoy ...");
	//}
}
