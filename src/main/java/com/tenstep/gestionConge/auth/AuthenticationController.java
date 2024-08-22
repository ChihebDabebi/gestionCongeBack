package com.tenstep.gestionConge.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Enumeration;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
     Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationService authenticationService;


    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request ){
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request , HttpServletResponse response){
        AuthenticationResponse authResponse = authenticationService.authenticate(request);
        String refreshToken = authResponse.getRefreshToken();
        System.out.println("Refresh Token: " + refreshToken);
// Assume this method exists and returns the refresh token
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true); // Prevent client-side access
        cookie.setSecure(true); // Only send cookie over HTTPS
        cookie.setPath("/"); // Path where the cookie is valid
        cookie.setMaxAge(60 * 60 * 24); // Cookie expiration time (e.g., 1 day)
        response.addCookie(cookie);
        return ResponseEntity.ok(authResponse);

    }
    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request , HttpServletResponse response) throws IOException {
        logRequestDetails(request);
        authenticationService.refreshToken(request,response);
    }
    private void logRequestDetails(HttpServletRequest request) {
        // Log request URL
        logger.info("Request URL: {}", request.getRequestURL());

        // Log request method
        logger.info("Request Method: {}", request.getMethod());

        // Log request headers
        logger.info("Request Headers:");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            logger.info("{}: {}", headerName, request.getHeader(headerName));
        }

        // Log request parameters
        logger.info("Request Parameters:");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            logger.info("{}: {}", paramName, request.getParameter(paramName));
        }
    }

}
