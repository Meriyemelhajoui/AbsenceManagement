package com.ensah.core.web.controllers;

import com.ensah.core.bo.*;
import com.ensah.core.config.ConfigAbsence;
import com.ensah.core.services.IAbsenceService;
import com.ensah.core.services.IEtudiantService;
import com.ensah.core.services.IInscriptionService;
import com.ensah.core.web.models.PersonModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class AbsenceController {


    @Autowired
    private IAbsenceService absenceService;




    @Autowired
    private IInscriptionService inscriptionService;




    @Autowired
    private ConfigAbsence configAbsence;



//    @Autowired
//    private IEtudiantService etudiantService;

        @GetMapping("/absence/liste")
        public String afficherListeAbsences(Model model) {
            List<Absence> absences = absenceService.getAllAbsences();
            model.addAttribute("absences", absences);
            return "absence/listeAbsences";
        }



    @RequestMapping(value = "/searchAbsenceById", method = RequestMethod.GET)
    public String searchAbsenceById(@RequestParam("idEtudiant") Long idEtudiant, Model model) {
        // Effectuez la recherche d'absence en utilisant l'ID d'étudiant=idUtilisateur
        // et récupérez les résultats dans une liste
        List<Absence> absences = absenceService.getAbsencesByIdInscription(idEtudiant);

        if (absences.isEmpty()) {
            // Aucune absence trouvée, affichez un message d'erreur
            String errorMessage = "Aucune absence trouvée pour l'identifiant d'étudiant " + idEtudiant;
            model.addAttribute("errorMessage", errorMessage);
            return "absence/erreurAbsences";
        } else

        {
            List<Long> idsInscription = new ArrayList<>();

            // Parcourez la liste des absences et récupérez les ID d'inscription
            for (Absence absence : absences) {
                Long idInscription = absence.getInscription().getIdInscription();
                idsInscription.add(idInscription);
            }

            // Ajoutez la liste des absences et des IDs d'inscription à l'objet Model pour les afficher dans la vue
            model.addAttribute("absences", absences);
            model.addAttribute("idsInscription", idsInscription);

            // Retournez le nom de la vue qui affiche les résultats des absences
            return "absence/listeAbsences";
        }
    }


    @GetMapping("/ficheAbsence")
    public String afficherFicheAbsence(@RequestParam("idInscription") Long idInscription, Model model) {
        // Récupérez l'inscription correspondante à l'ID d'inscription
        Inscription inscription = inscriptionService.getByID(idInscription);

        if (inscription != null) {
            // Récupérez les informations nécessaires à afficher dans la fiche d'absence
            Etudiant etudiant = inscription.getEtudiant();
            List<Absence> absences = absenceService.getAbsencesByIdInscription(idInscription);

            // Ajoutez les informations à l'objet Model
            model.addAttribute("etudiant", etudiant);
            model.addAttribute("absences", absences);

            // Retournez le nom de la vue qui affiche la fiche d'absence

            model.addAttribute("errorMessage");
            return "absence/ficheAbsence";
        } else {
            // L'inscription n'a pas été trouvée, affichez un message d'erreur
            String errorMessage = "Aucune inscription trouvée pour l'identifiant d'inscription " + idInscription;
            model.addAttribute("errorMessage", errorMessage);
            return "absence/erreurAbsences";
        }
    }


    private boolean peutSupprimerAbsence(Absence absence ) {
        // Récupérer le seuil de suppression à partir de la configuration de l'application
        int seuil = configAbsence.getSeuilSuppressionAbsence();

        // Récupérer la date actuelle
        LocalDate currentDate = LocalDate.now();

        // Récupérer la date de l'absence
        LocalDate absenceDate = absence.getLocalDate();

        // Calculer la différence en jours entre la date actuelle et la date de l'absence
        long daysDifference = ChronoUnit.DAYS.between(absenceDate, currentDate);

        // Vérifier si la différence est inférieure au seuil
        return daysDifference < seuil;
    }


    // Methode de suppression d asence :
    @PostMapping("/supprimerAbsence")
    public String supprimerAbsence(@RequestParam("idAbsence") Long idAbsence , Model model) {

            // Conversion de L id de Absence de String vers Long

        // Récupérer l'absence à supprimer
        Absence absence = absenceService.getAbsenceByIdAbsence(idAbsence);
        System.out.println(absence.getIdAbsence());

        // Vérifier si l'absence peut être supprimée
        if (peutSupprimerAbsence(absence) && absence != null) {
            // Supprimer l'absence
            absenceService.SupprimerAbsenceByIdAbsence(idAbsence);
            return "absence/SuccesAbsence";
        } else {
            String errorMessage = "La suppression ne peut pas etre effectue car elle a depasse le seuil";
            model.addAttribute("errorMessage", errorMessage);


            return "absence/erreurAbsences";
        }


    }}








