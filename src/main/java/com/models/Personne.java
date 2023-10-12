package com.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    @Column(nullable = true)
    private String posteActuel;

   @OneToOne(mappedBy = "personne")
    private PersonneEntreprise personneEntreprise;

    // Ajoutez le getter pour la date de naissance
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setPosteActuel(String posteActuel) {
        this.posteActuel = posteActuel;
    }

    public PersonneEntreprise getPersonneEntreprise() {
        return personneEntreprise;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
