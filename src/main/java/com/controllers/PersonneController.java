package com.controllers;

import com.models.Personne;
import com.models.PersonneEntreprise;
import com.repositories.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RestController
public class PersonneController {

    @Autowired
    private PersonneRepository personneRepository;
    @PostMapping("/api/persons")
    public ResponseEntity<String> addPerson(@RequestBody Personne person) {
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(person.getDateOfBirth(), currentDate).getYears();

        if (age < 0 || age >= 150) {
            return ResponseEntity.badRequest().body("L'âge de la personne n'est pas valide.");
        }

        // Si l'âge est valide, ajoutez la personne à la base de données
        personneRepository.save(person);
        return ResponseEntity.ok("Personne enregistrée avec succès.");
    }

    @GetMapping("/all-persons-emplois")
    public List<Personne> getAllPersons() {
        // Récupérez toutes les personnes enregistrées par ordre alphabétique
        List<Personne> personnes = personneRepository.findAllByOrderByFirstNameAscLastNameAsc();

        for (Personne personne : personnes) {
            // Récupérez l'emploi actuel (si existant)
            PersonneEntreprise employment = personne.getPersonneEntreprise();

            if (employment != null) {
                String posteActuel = employment.getPosteOccupe();
                personne.setPosteActuel(posteActuel);
            }
        }

        return personnes;
    }

}
