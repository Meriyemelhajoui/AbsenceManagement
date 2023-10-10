package com.ensah.core.services.impl;


import com.ensah.core.bo.Matiere;
import com.ensah.core.dao.IMatiereDAO;
import com.ensah.core.services.IMatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class IMatiereServiceImpl implements IMatiereService {

    @Autowired
    private IMatiereDAO matiereDAO;


    public List<Matiere> getAllMatieres() {
        return matiereDAO.findAll();
    }


    public Matiere getMatiereById(Long id){
        return matiereDAO.findByIdMatiere(id);
    }

    @Override
    public Long getIdMatiereByNom(String nomMatiere) {
        return matiereDAO.findIdMatiereByNom(nomMatiere);
    }


}
