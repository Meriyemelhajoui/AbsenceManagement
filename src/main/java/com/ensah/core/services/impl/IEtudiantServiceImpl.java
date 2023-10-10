package com.ensah.core.services.impl;

import com.ensah.core.bo.Etudiant;
import com.ensah.core.dao.IEtudiantDao;
import com.ensah.core.dao.IUtilisateurDao;
import com.ensah.core.services.IEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
public class IEtudiantServiceImpl implements IEtudiantService {

    @Autowired
    IEtudiantDao etudiantDao;
    @Override
    public Etudiant enregistrerEtu(Etudiant etudiant) {
        return etudiantDao.save(etudiant);
    }

    @Override
    public Optional<Etudiant> getEtudiantById(Long id) {
        return etudiantDao.findById(id);
    }

    @Override
    public Optional<Etudiant> getEtudiantByIdUtilisateur(Long idUtilisateur) {
        return etudiantDao.findEtudiantByIdUtilisateur(idUtilisateur);
    }
}
