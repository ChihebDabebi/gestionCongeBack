package com.tenstep.gestionConge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
    private String user_id;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate dateEmbauche;
    private int nbrJours;
    private String cin;
    private Long age ;
}
