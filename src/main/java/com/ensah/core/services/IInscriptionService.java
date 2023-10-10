package com.ensah.core.services;

import com.ensah.core.bo.Etudiant;
import com.ensah.core.bo.Inscription;

import java.util.List;

public interface IInscriptionService {
    public List<Etudiant> getEtudiantsByNiveau(Long idNiveau);

    public Inscription getByID(Long id);

    public Long getIdInscriptionFromIdEtudiant(Long idEtudiant);
}
