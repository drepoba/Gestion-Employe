package com.controllers;
import com.models.Personne;
import com.models.PersonneEntreprise;
import com.repositories.PersonneEntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class PersonneEntrepriseController {
    @Autowired
    private PersonneEntrepriseRepository personneEntrepriseRepository;

    @PostMapping("/add-employment")
    public ResponseEntity<String> addEmployment(@RequestBody PersonneEntreprise personneEntreprise) {
        try {
            personneEntrepriseRepository.save(personneEntreprise);
            return ResponseEntity.status(HttpStatus.CREATED).body("L'emploi a été ajouté avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de l'ajout de l'emploi : " + e.getMessage());
        }
    }

    @GetMapping("/emplois")
    public ResponseEntity<List<PersonneEntreprise>> getEmploisEntreDeuxDates(
            @RequestParam Long personneId,
            @RequestParam String startDate1,
            @RequestParam String endDate1,
            @RequestParam String startDate2,
            @RequestParam String endDate2
    ) {
        Personne personne = new Personne();
        personne.setId(personneId);

        LocalDate startDate1Parsed = LocalDate.parse(startDate1);
        LocalDate endDate1Parsed = LocalDate.parse(endDate1);
        LocalDate startDate2Parsed = LocalDate.parse(startDate2);
        LocalDate endDate2Parsed = LocalDate.parse(endDate2);

        List<PersonneEntreprise> emplois = personneEntrepriseRepository.findByPersonneAndDateBeginBetweenAndDateEndBetween(
                personne, startDate1Parsed, endDate1Parsed, startDate2Parsed, endDate2Parsed
        );

        return new ResponseEntity<>(emplois, HttpStatus.OK);
    }

}
