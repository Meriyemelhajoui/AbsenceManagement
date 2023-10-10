package com.ensah.core.services;

import com.ensah.core.bo.Matiere;


import java.util.List;
import java.util.Optional;

public interface IMatiereService  {

    public List<Matiere> getAllMatieres();
    public Matiere getMatiereById(Long id);

    public Long getIdMatiereByNom(String nomMatiere);



}
