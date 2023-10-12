package com.controllers;

import com.models.Personne;
import com.repositories.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntrepriseController {
    @Autowired
    private PersonneRepository personneRepository;

    @GetMapping("/{entrepriseId}/personnes")
    public ResponseEntity<List<Personne>> getPersonsByEntreprise(@PathVariable Long entrepriseId) {
        List<Personne> personnes = personneRepository.findByEntrepriseId(entrepriseId);
        return new ResponseEntity<>(personnes, HttpStatus.OK);
    }
}
