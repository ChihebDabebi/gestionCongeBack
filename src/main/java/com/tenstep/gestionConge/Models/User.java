package com.tenstep.gestionConge.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String user_id;
    private String nom;
    private String prenom;
    private String email;
    private String cin;
    private Long age ;
    private String password;
    private String role_id ;
}
