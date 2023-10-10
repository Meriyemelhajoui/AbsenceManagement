package com.ensah.core.web.controllers;

import com.ensah.core.bo.*;
import com.ensah.core.services.*;
import com.ensah.core.services.impl.IEnseignatServiceImpl;
import com.ensah.core.services.impl.IInscriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.lang.Long;

import java.util.Date;

import java.util.List;
import java.util.Optional;

@Controller
public class EnseignantController {

    @Autowired
    private INiveauService niveauService;

    @Autowired
    private IMatiereService matiereService;


    @Autowired
    private IInscriptionService inscriptionService;

    @Autowired
    private ITypeSequenceService typeSequenceService;

    @Autowired
    private IAbsenceService absenceService;

   @Autowired
   private IEtudiantService etudiantService;


    @GetMapping("/enseignant/formulaire")

    public String afficherFormulaire( Model model) {
        List<Niveau> niveaux = niveauService.getAllNiveaux();
        model.addAttribute("niveaux", niveaux);
        List<Matiere> matieres = matiereService.getAllMatieres();
        model.addAttribute("matieres", matieres);
        List<TypeSeance> typesSequences = typeSequenceService.getAllSeances();
        model.addAttribute("typesSequences", typesSequences);

        return "prof/formProf";
    }


    @PostMapping("/enseignant/selectionnerNiveau")
    public String selectionnerNiveau(@RequestParam("idNiveau") Long idNiveau, @RequestParam("nomMatiere") String nomMatiere, @RequestParam("typeSequence") Long idTypeSeance,Model model) {


        List<Etudiant> etudiants = inscriptionService.getEtudiantsByNiveau(idNiveau);
        model.addAttribute("etudiants", etudiants);


        List<Niveau> niveaux = niveauService.getAllNiveaux();
        model.addAttribute("niveaux", niveaux);
        List<Matiere> matieres = matiereService.getAllMatieres();
        model.addAttribute("matieres", matieres);
        List<TypeSeance> typesSeances = typeSequenceService.getAllSeances();
        model.addAttribute("typesSeances", typesSeances);

        // Ajouter les attributs idTypeSeance et nomMatiere au modèle
        model.addAttribute("idTypeSeance", idTypeSeance);
        model.addAttribute("nomMatiere", nomMatiere);



        return "prof/formProf";
    }



    @PostMapping("/absence/enregistrer")
    public String enregistrerAbsence(@RequestParam("idEtudiant") Long idEtudiant,Model model,
                                     @RequestParam("dateHeureDebutAbsence") String dateHeureDebutAbsence,
                                     @RequestParam("dateHeureFinAbsence") String dateHeureFinAbsence,
                                     @ModelAttribute("idTypeSeance") Long idTypeSeance,
                                     @ModelAttribute("nomMatiere") String nomMatiere)


    {
        try {

            Long idInscription = inscriptionService.getIdInscriptionFromIdEtudiant(idEtudiant);



            Inscription inscription = inscriptionService.getByID(idInscription);



            Long idMatiere = matiereService.getIdMatiereByNom(nomMatiere);

            Matiere matiere = matiereService.getMatiereById(idMatiere);

            TypeSeance typeSeance= typeSequenceService.getTypeSeanceById(idTypeSeance);

            String AliasTypeSeance = typeSequenceService.getAliasById(idTypeSeance);



            if (inscription != null && matiere != null) {


                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                Date dateHeureDAbsence = dateFormat.parse(dateHeureDebutAbsence);
                Date dateHeureFAbsence = dateFormat.parse(dateHeureFinAbsence);



                Absence absence = new Absence();
                absence.setInscription(inscription);
                absence.setMatiere(matiere);
                absence.setDateHeureDebutAbsence(dateHeureDAbsence);
                absence.setDateHeureFinAbsence(dateHeureFAbsence);
                absence.setTypeSeance(typeSeance);

                absence.setTypeSaisie(AliasTypeSeance);


                absenceService.enregistrerAbsence(absence);
                String SuccesMessage = "L'enregistrement de l'absence a ete bien effectue ! ";
                model.addAttribute("SuccessMessage", SuccesMessage);

                return "/prof/SuccesEnregistrement";


            } else {
                String message="L'enregistrement de l'absence n est pas reussi !";
                        model.addAttribute("message", message);
                return "/prof/error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "/prof/error";
        }
    }
















    // methode pour la saisie commune des absences //

    @PostMapping("/absence/enregistrer-multiple")
    public String enregistrerAbsencesMultiples(@RequestParam("dateHeureDebutAbsenceC") String dateHeureDebutAbsenceC,
                                               @RequestParam("dateHeureFinAbsenceC") String dateHeureFinAbsenceC,
                                               @RequestParam("absentStudents") List<String> idEtudiants,
                                               @ModelAttribute("idTypeSeance") Long idTypeSeance,
                                               @ModelAttribute("nomMatiere") String nomMatiere) {
        try {
            // Convertir les chaînes de date en objets Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date dateHeureDebutAbsence = dateFormat.parse(dateHeureDebutAbsenceC);
            Date dateHeureFinAbsence = dateFormat.parse(dateHeureFinAbsenceC);

            // Enregistrer les absences pour chaque étudiant
            for (String idEtudiant : idEtudiants) {
                try {
                    Long idEtudiantLong = Long.valueOf(idEtudiant);
                    // Récupérer les informations de l'étudiant
                    Long idInscription = inscriptionService.getIdInscriptionFromIdEtudiant(idEtudiantLong);

                    Inscription inscription = inscriptionService.getByID(idInscription);

                    Long idMatiere = matiereService.getIdMatiereByNom(nomMatiere);

                    Matiere matiere = matiereService.getMatiereById(idMatiere);

                    TypeSeance typeSeance = typeSequenceService.getTypeSeanceById(idTypeSeance);

                    String aliasTypeSeance = typeSequenceService.getAliasById(idTypeSeance);

                    if (inscription != null && matiere != null) {
                        // Créer et enregistrer l'absence
                        Absence absence = new Absence();
                        absence.setInscription(inscription);
                        absence.setMatiere(matiere);
                        absence.setDateHeureDebutAbsence(dateHeureDebutAbsence);
                        absence.setDateHeureFinAbsence(dateHeureFinAbsence);
                        absence.setTypeSeance(typeSeance);
                        absence.setTypeSaisie(aliasTypeSeance);
                        absenceService.enregistrerAbsence(absence);
                    }
                } catch (NumberFormatException e) {
                    // Gérer le cas où l'ID de l'étudiant n'est pas un nombre valide
                    return "redirect:/prof/error";
                }
            }

            return "redirect:/prof/success";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/prof/error";
        }
    }






}















