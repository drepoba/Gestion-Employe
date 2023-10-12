package com.repositories;

import com.models.Personne;
import com.models.PersonneEntreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface PersonneEntrepriseRepository  extends JpaRepository<PersonneEntreprise,Long> {
    List<PersonneEntreprise> findByPersonneAndDateBeginBetweenAndDateEndBetween(
            Personne personne, LocalDate startDate1, LocalDate endDate1, LocalDate startDate2, LocalDate endDate2
    );
}
