package com.ensah.core.dao;

import com.ensah.core.bo.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface INiveauDao extends JpaRepository<Niveau , Long> {

    public List<Niveau> findAll();
}
