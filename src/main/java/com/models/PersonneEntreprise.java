package com.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonneEntreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate dateBegin;

    private LocalDate dateEnd;

    @NotNull
    private String posteOccupe;

    @OneToOne
    @JoinColumn(name = "entrepriseId")
    private Entreprise entreprise;

    @OneToOne
    @JoinColumn(name = "personneId")
    private  Personne personne;

    @OneToOne(mappedBy = "personne")
    private PersonneEntreprise personneEntreprise;

    public String getPosteOccupe() {
        return posteOccupe;
    }
}
