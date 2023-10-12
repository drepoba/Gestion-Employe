package com.repositories;

import com.models.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonneRepository extends JpaRepository<Personne,Long> {
    List<Personne> findAllByOrderByFirstNameAscLastNameAsc();

    @Query("SELECT p FROM Personne p JOIN p.personneEntreprise pe WHERE pe.entreprise.id = :entrepriseId")
    List<Personne> findByEntrepriseId(@Param("entrepriseId") Long entrepriseId);


}
