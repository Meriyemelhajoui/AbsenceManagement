package com.ensah.core.services.impl;


import com.ensah.core.bo.Etudiant;
import com.ensah.core.bo.Inscription;
import com.ensah.core.dao.IInscriptionDao;
import com.ensah.core.services.IInscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class IInscriptionServiceImpl implements IInscriptionService {

    @Autowired
    private IInscriptionDao inscriptionDao;

    public List<Etudiant> getEtudiantsByNiveau(Long idNiveau) {
        List<Inscription> inscriptions = inscriptionDao.findByNiveauIdNiveau(idNiveau);
        Set<Etudiant> etudiants = new HashSet<>();

        for (Inscription inscription : inscriptions) {
            etudiants.add(inscription.getEtudiant());
        }

        return new ArrayList<>(etudiants);
    }

    @Override
    public Inscription getByID(Long id) {
        return inscriptionDao.findByIdInscription(id);
    }


    public Long getIdInscriptionFromIdEtudiant(Long idEtudiant) {
        return inscriptionDao.findInscriptionIdByEtudiantId(idEtudiant);
    }




}
