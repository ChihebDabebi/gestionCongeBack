package com.tenstep.gestionConge.Repositories;

import com.tenstep.gestionConge.Models.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification,String> {
}
