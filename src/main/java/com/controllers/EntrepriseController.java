package com.controllers;

import com.models.Entreprise;
import com.models.Personne;
import com.repositories.EntrepriseRepository;
import com.repositories.PersonneRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RestController
@RequestMapping("/api/entreprises")
@Api(tags = "Entreprises", description = "API pour gérer les entreprises")
public class EntrepriseController {
    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @GetMapping("/{entrepriseId}/personnes")
    @ApiOperation("Récupère la liste de toutes les personnes d'une entreprise")
    public ResponseEntity<List<Personne>> getPersonsByEntreprise(@PathVariable Long entrepriseId) {
        List<Personne> personnes = personneRepository.findByEntrepriseId(entrepriseId);
        return new ResponseEntity<>(personnes, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<String> addEntreprise(@RequestBody Entreprise entreprise) {
        // Si l'âge est valide, ajoutez la personne à la base de données
        if (entreprise==null) {
            return ResponseEntity.badRequest().body("Aucune donnée envoyée.${entreprise}");
        }
        entrepriseRepository.save(entreprise);
        return ResponseEntity.ok("Entreprise enregistrée avec succès.");
    }
}
