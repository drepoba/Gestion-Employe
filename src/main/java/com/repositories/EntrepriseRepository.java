package com.repositories;

import com.models.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise,Long> {
}
