package com.ensah.core.dao;

import com.ensah.core.bo.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IInscriptionDao extends JpaRepository<Inscription , Long> {


    public List<Inscription> findByNiveauIdNiveau(Long idNiveau);

    public Inscription findByIdInscription(Long id);

        @Query("SELECT i.idInscription FROM Inscription i WHERE i.etudiant.idUtilisateur = :idEtudiant")
        Long findInscriptionIdByEtudiantId(@Param("idEtudiant") Long idEtudiant);


}
