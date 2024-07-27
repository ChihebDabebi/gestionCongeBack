package com.tenstep.gestionConge.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenstep.gestionConge.Models.Role;
import com.tenstep.gestionConge.Models.User;
import com.tenstep.gestionConge.Repositories.UserRepository;
import com.tenstep.gestionConge.config.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;

    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);


    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .user_id(UUID.randomUUID().toString().split("-")[0])
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.Employe)
                .dateEmbauche(request.getDateEmbauche())
                .cin(request.getCin())
                .age(request.getAge())
                .build();
        var savedUser =repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);


        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();


    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken ;
        final String email ;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
        }
        refreshToken = authHeader.substring(7);
        email = jwtService.extractEmail(refreshToken);
        Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
        logger.trace(""+email);
        if(email != null){
            User user =  this.repository.findByEmail(email).orElseThrow();
            logger.trace("token validation :"+jwtService.isTokenValid(refreshToken,user));
            if(jwtService.isTokenValid(refreshToken,user)){
                var accessToken = jwtService.generateToken(user);
                var authenticationResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                logger.debug("vvccc"+authenticationResponse);
                objectMapper.writeValue(response.getOutputStream(),authenticationResponse);


            }
        }
    }
}
