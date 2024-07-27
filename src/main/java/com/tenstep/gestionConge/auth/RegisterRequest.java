package com.tenstep.gestionConge.auth;

import com.tenstep.gestionConge.Models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private LocalDate dateEmbauche;
    private String cin;
    private Long age ;
}
