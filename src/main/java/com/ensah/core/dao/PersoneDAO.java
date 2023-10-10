package com.ensah.core.dao;

import com.ensah.core.bo.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersoneDAO extends JpaRepository<Utilisateur, Long> {

    @Query("SELECT u FROM Utilisateur u WHERE u.cin = :userinput OR u.nom = :userinput")
    List<Utilisateur> getPersonByCINORName(@Param("userinput") String userinput);
}
