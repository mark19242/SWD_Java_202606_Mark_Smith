package org.apprenti.app_bff.controller;

import java.util.List;

import org.apprenti.app_bff.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AppController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;
    private final JdbcUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final JdbcClient jdbcClient;

    public AppController(AuthenticationManager authManager,
            TokenService tokenService,
            JdbcUserDetailsManager userDetailsManager,
            PasswordEncoder passwordEncoder,
            JdbcClient jdbcClient) {
        this.authManager = authManager;
        this.tokenService = tokenService;
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
        this.jdbcClient = jdbcClient;
    }

    // Records for DTOs
    public record AuthRequest(String username, String password) {

    }

    public record AuthResponse(String token) {

    }

    public record UserSummary(String userName, boolean enabled) {

    }

    ;

    // --- Authentication Endpoints ---

    @PostMapping("/auth/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest req) {
        if (userDetailsManager.userExists(req.username())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        var user = User.builder()
                .username(req.username())
                .password(passwordEncoder.encode(req.password()))
                .roles("USER")
                .build();

        userDetailsManager.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password())
        );
        String token = tokenService.generateToken(auth);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    // --- Authenticated Hello Endpoint ---
    @GetMapping("/greet")
    public ResponseEntity<String> greet(Authentication authentication) {
        return ResponseEntity.ok("Hello, " + authentication.getName());
    }

    // --- Authenticated Domain Endpoints ---
    @GetMapping("/admin/users")
    public List<UserSummary> getAllUsers() {
        return jdbcClient.sql("SELECT username, enabled FROM users").query(UserSummary.class).list();
    }
}
