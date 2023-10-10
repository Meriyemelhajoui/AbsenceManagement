package com.ensah.core.dao;

import com.ensah.core.bo.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.core.bo.Utilisateur;

import java.util.List;

public interface IUtilisateurDao extends JpaRepository<Utilisateur, Long>, GenericJpa<Utilisateur, Long> {

}
