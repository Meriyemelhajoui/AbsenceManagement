package com.ensah.core.dao;

import com.ensah.core.bo.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IMatiereDAO extends JpaRepository<Matiere,Long> {

    public List<Matiere> findAll();
    public Matiere findByIdMatiere(Long id );

    @Query("SELECT m.idMatiere FROM Matiere m WHERE m.nom = :nomMatiere")
    public Long findIdMatiereByNom(@Param("nomMatiere") String nomMatiere);



}
