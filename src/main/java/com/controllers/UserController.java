package com.controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.repositories.PersonneRepository;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.configs.security.JwtOutils;
import com.configs.security.MyBCryptPasswordEncoder;
import com.models.*;
import com.repositories.RoleRepository;
import com.repositories.UserRepository;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Arrays;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("**")
@Api(tags = "Utilisateur", description = "API pour gérer les utilisateur")
public class UserController {


    private MyBCryptPasswordEncoder myBCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private JwtOutils jwtOutils;

    @Autowired
    private RoleRepository roleRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserRepository UserRepository,
                          MyBCryptPasswordEncoder myBCryptPasswordEncoder) {
        this.userRepository = UserRepository;
        this.myBCryptPasswordEncoder = myBCryptPasswordEncoder;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponse> signIn(@RequestBody AuthRequest authRequest) {
        User user = userRepository.findByUsername(authRequest.username);
        if (user != null && myBCryptPasswordEncoder.matches(authRequest.password, user.getPassword())) {
            // Créez un jeton JWT pour l'utilisateur et créez une instance AuthResponse
            String jwtToken = jwtOutils.create(user);
            AuthResponse authResponse = new AuthResponse(jwtToken);

            // Retournez une réponse ResponseEntity avec l'objet AuthResponse et le code HTTP OK (200)
            return ResponseEntity.ok(authResponse);
        }
        // En cas d'échec de l'authentification, retournez une réponse ResponseEntity avec le code HTTP Unauthorized (401)
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody Personne personne) throws JsonProcessingException {
        if (personne == null) {
            logger.info("[sign-up] => No information provided");
            return ResponseEntity.badRequest().body("No information provided");
        }
        User user = userRepository.findByUsername(personne.getUser().getUsername());
        if (user != null) {
            logger.info("[sign-up] => Username exists");
            return ResponseEntity.badRequest().body("Username exists");
        }

        logger.info("[sign-up] => save");
        User savedUser = userRepository.save(new User(
                personne.getUser().getUsername(),
                myBCryptPasswordEncoder.encode(personne.getUser().getPassword()),
                Arrays.asList(roleRepository.findByName("ROLE_USER"))
        ));

        personne.setUser(savedUser);
        personneRepository.save(personne);



        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(savedUser);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonResponse);
    }


}
