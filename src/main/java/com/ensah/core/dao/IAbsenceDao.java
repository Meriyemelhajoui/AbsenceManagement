package com.ensah.core.dao;

import com.ensah.core.bo.Absence;
import com.ensah.core.bo.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IAbsenceDao extends JpaRepository<Absence,Long> {

    public Absence save(Absence absence);
    public List<Absence> findAll();




    @Query("SELECT a FROM Absence a WHERE a.inscription.idInscription = :inscriptionId")
    List<Absence> findAbsencesByIdInscription(@Param("inscriptionId") Long inscriptionId);

    public Absence findAbsenceByIdAbsence(Long id);















    // Methode pour supprimer une absence //

    public void deleteAbsenceByIdAbsence(Long id);



}
