package com.ensah.core.services;

import com.ensah.core.bo.Absence;
import com.ensah.core.bo.Etudiant;

import java.util.List;

public interface IAbsenceService {


     public Absence enregistrerAbsence(Absence absence);

     public List<Absence> getAllAbsences();



     public List<Absence> getAbsencesByIdInscription(Long id);

     public Absence getAbsenceByIdAbsence(long id);

     public void SupprimerAbsenceByIdAbsence(Long id);


}
