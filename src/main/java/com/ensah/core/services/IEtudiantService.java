package com.ensah.core.services;

import com.ensah.core.bo.Etudiant;

import java.util.Optional;

public interface IEtudiantService {

    public Etudiant enregistrerEtu(Etudiant etudiant);
    Optional<Etudiant> getEtudiantById(Long id);

    public Optional<Etudiant> getEtudiantByIdUtilisateur(Long idUtilisateur);


}
