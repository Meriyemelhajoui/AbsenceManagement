package com.ensah.core.bo;


import com.ensah.core.config.ConfigAbsence;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Absence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAbsence;

	private Date dateHeureDebutAbsence;

	private Date dateHeureFinAbsence;

	private int etat;

	private String typeSaisie;


	@ManyToOne
	@JoinColumn(name = "idMatiere")
	private Matiere matiere;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable( joinColumns = @JoinColumn(name="idAbsence"),
				inverseJoinColumns = @JoinColumn(name="idPieceJustificative"))
	private Set<PieceJustificative> pieceJustificative;

	@ManyToOne
	@JoinColumn(name = "idInscription")
	private Inscription inscription;

	@ManyToOne
	@JoinColumn(name = "idTypeSeance")
	private TypeSeance typeSeance;
	
	@ManyToOne
	@JoinColumn(name = "idEnseignat")
	private Enseignant observateur;




	public Long getIdAbsence() {
		return idAbsence;
	}

	public void setIdAbsence(Long idAbsence) {
		this.idAbsence = idAbsence;
	}

	public Date getDateHeureDebutAbsence() {
		return dateHeureDebutAbsence;
	}

	public void setDateHeureDebutAbsence(Date dateHeureDebutAbsence) {
		this.dateHeureDebutAbsence = dateHeureDebutAbsence;
	}

	public LocalDate getLocalDate() {
		return dateHeureDebutAbsence.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}


	// Cette methode pour extraire juste dd/mm/yyyy a partir de la date d'absence
	public String getFormattedDate() {
		// Récupérer la date d'absence
		LocalDate dateAbsence = getLocalDate();


		// Formater la date au format souhaité (jour/mois/année)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dateAbsence.format(formatter);
	}



	public String getFormattedStartTime() {
		LocalTime startTime = dateHeureDebutAbsence.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
		return startTime.format(DateTimeFormatter.ofPattern("HH:mm"));
	}

	public String getFormattedEndTime() {
		LocalTime endTime = dateHeureFinAbsence.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
		return endTime.format(DateTimeFormatter.ofPattern("HH:mm"));
	}


	public Date getDateHeureFinAbsence() {
		return dateHeureFinAbsence;
	}

	public void setDateHeureFinAbsence(Date dateHeureFinAbsence) {
		this.dateHeureFinAbsence = dateHeureFinAbsence;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public String getTypeSaisie() {
		return typeSaisie;
	}

	public void setTypeSaisie(String typeSaisie) {
		this.typeSaisie = typeSaisie;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Set<PieceJustificative> getPieceJustificative() {
		return pieceJustificative;
	}

	public void setPieceJustificative(Set<PieceJustificative> pieceJustificative) {
		this.pieceJustificative = pieceJustificative;
	}


	// Stockage de ID matiere a partir de Matiere Object
	public void SetIdMatiere( Matiere matiere, Long id){
		if(matiere==null){
			this.matiere=matiere;
		}else {
			this.matiere.setIdMatiere(id);
		}
	}

	// Stockage de ID typeSeance a partir de Type Seance object

	public void SetIdTypeSeance(TypeSeance typeSeance, Long id){
		if(typeSeance==null){
			this.typeSeance=typeSeance;
		}else {
			this.typeSeance.setIdTypeSeance(id);
		}
	}


	// Stockage de ID Inscription  a partir de Inscription object


	public void SetIdInscription(Inscription inscription , Long id){
		if(inscription==null){
			this.inscription=inscription;
		}else {
			this.inscription.setIdInscription(id);
		}
	}




	// Methode qui verifie si la suppression d une absence est possible ou nn //




	public Inscription getInscription() {
		return inscription;
	}

	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}

	public TypeSeance getTypeSeance() {
		return typeSeance;
	}

	public void setTypeSeance(TypeSeance typeSeance) {
		this.typeSeance = typeSeance;
	}

	public Enseignant getObservateur() {
		return observateur;
	}

	public Long getIdInscription(Inscription inscription ){
		if(inscription==null){
			 this.inscription = new Inscription();
		}else{
			return inscription.getIdInscription();
		}
		return this.inscription.getIdInscription();
	}



	public void setObservateur(Enseignant observateur) {
		this.observateur = observateur;
	}

	
}