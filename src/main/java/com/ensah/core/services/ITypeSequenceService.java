package com.ensah.core.services;

import com.ensah.core.bo.TypeSeance;

import java.util.List;

public interface ITypeSequenceService {
    public List<TypeSeance> getAllSeances();

    public TypeSeance getTypeSeanceById(Long id);

    public String getAliasById(Long id);
}
