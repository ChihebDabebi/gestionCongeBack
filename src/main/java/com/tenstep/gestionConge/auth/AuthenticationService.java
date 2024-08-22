package com.tenstep.gestionConge.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenstep.gestionConge.Models.Role;
import com.tenstep.gestionConge.Models.User;
import com.tenstep.gestionConge.Repositories.UserRepository;
import com.tenstep.gestionConge.config.JwtService;
import jakarta.servlet.http.Cookie;
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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
                .nbrJours((int)Math.round(ChronoUnit.MONTHS.between(request.getDateEmbauche(), LocalDate.now())*1.80))
                .cin(request.getCin())
                .age(request.getAge())
                .build();
        var savedUser =repository.save(user);
        var jwtToken = jwtService.generateToken(savedUser);
        var refreshToken = jwtService.generateRefreshToken(savedUser);


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
        // Extract refresh token from cookies
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies);
        String refreshToken = null;
        for (Cookie cookie : cookies) {
            if ("refreshToken".equals(cookie.getName())) {
                refreshToken = cookie.getValue();
                System.out.println("refresh" + refreshToken);
                break;
            }
        }

        if (refreshToken == null) {
            // Handle case where refresh token is not found
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Refresh token not found");
            return;
        }

        // Extract email from the refresh token
        String email = jwtService.extractEmail(refreshToken);
        Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
        logger.trace("Extracted email: " + email);

        if (email != null) {
            User user = this.repository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
            logger.trace("Token validation: " + jwtService.isTokenValid(refreshToken, user));
            if (jwtService.isTokenValid(refreshToken, user)) {
                // Generate new access token
                var accessToken = jwtService.generateToken(user);

                // Create new authentication response
                var authenticationResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken) // Include refresh token in response if needed
                        .build();

                // Optionally, set the refresh token in a new cookie
                Cookie newCookie = new Cookie("refreshToken", refreshToken);
                newCookie.setHttpOnly(true);
                newCookie.setSecure(true);
                newCookie.setPath("/"); // Set the path to ensure it is accessible
                newCookie.setMaxAge(60 * 60 * 24); // Set cookie expiration time
                response.addCookie(newCookie);

                // Send the response
                response.setContentType("application/json");
                objectMapper.writeValue(response.getOutputStream(), authenticationResponse);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid refresh token");
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid refresh token");
        }
    }
}
