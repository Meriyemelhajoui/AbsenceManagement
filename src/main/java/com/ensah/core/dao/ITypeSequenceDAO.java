package com.ensah.core.dao;

import com.ensah.core.bo.TypeSeance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ITypeSequenceDAO extends JpaRepository<TypeSeance, Long> {

    public List<TypeSeance> findAll();

   public Optional<TypeSeance> findById(Long id);


    @Query("SELECT t.alias FROM TypeSeance t WHERE t.idTypeSeance = :idTypeSeance")
    String findAliasByIdTypeSeance(@Param("idTypeSeance") Long idTypeSeance);


}
