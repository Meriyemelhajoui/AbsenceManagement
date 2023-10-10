package com.ensah.core.dao;

import com.ensah.core.bo.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEnseignantDao extends JpaRepository<Enseignant, Long> {


}
