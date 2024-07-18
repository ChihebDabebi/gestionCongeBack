package com.tenstep.gestionConge.dto;

import com.tenstep.gestionConge.Models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String user_id;
    private String nom;
    private String prenom;
    private String email;
    private String cin;
    private Long age ;
}
