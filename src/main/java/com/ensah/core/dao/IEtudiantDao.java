package com.ensah.core.dao;

import com.ensah.core.bo.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEtudiantDao extends JpaRepository<Etudiant,Long> {

    // Recherche un étudiant par son ID
    Optional<Etudiant> findById(Long id);

    // Enregistrer l etudiant dans la base de donne
    Etudiant save(Etudiant etudiant);

    Optional<Etudiant> findEtudiantByIdUtilisateur(Long idUtilisateur);
}
