package com.ensah.core.services.impl;

import com.ensah.core.bo.TypeSeance;
import com.ensah.core.dao.ITypeSequenceDAO;
import com.ensah.core.services.ITypeSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ITypeSeanceImpl implements ITypeSequenceService {


    @Autowired
    private ITypeSequenceDAO typeSequenceDAO;
    @Override
    public List<TypeSeance> getAllSeances() {
        return typeSequenceDAO.findAll();
    }

    @Override
    public TypeSeance getTypeSeanceById(Long id) {
        Optional<TypeSeance> typeSeanceOptional = typeSequenceDAO.findById(id);
        if (typeSeanceOptional.isPresent()) {
            return typeSeanceOptional.get();
        } else {
            // Handle the case when the TypeSeance with the specified id is not found
            throw new NotFoundException("TypeSeance not found for id: " + id);
        }
    }

    @Override
    public String getAliasById(Long id) {
        return typeSequenceDAO.findAliasByIdTypeSeance(id);
    }

    // Other methods of the TypeSeanceService interface
    // ...
}



