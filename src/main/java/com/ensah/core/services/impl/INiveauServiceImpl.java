package com.ensah.core.services.impl;


import com.ensah.core.bo.Niveau;
import com.ensah.core.dao.INiveauDao;
import com.ensah.core.services.INiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class INiveauServiceImpl implements INiveauService {
    @Autowired
    public INiveauDao niveauDao;


    @Override
    public List<Niveau> getAllNiveaux() {
        return niveauDao.findAll();
    }
}
