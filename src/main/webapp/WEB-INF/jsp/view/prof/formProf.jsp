<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="../fragments/userheader.jsp" />
<style>
    body {
        display: flex;
        flex-direction: column;
        align-items: center;
        background-color: #f2f2f2;
        font-family: Arial, sans-serif;
        color: #333333;
    }

    form {
        margin-bottom: 30px;
        text-align: center;
    }

    .form-group {
        margin-bottom: 20px;
        text-align: left;
    }

    .students-table {
        width: 100%;
        margin-top: 30px;
        border-collapse: collapse;
        text-align: center;
        background-color: #ffffff; /* Updated background color */
        color: #333333; /* Updated text color */
    }

    .students-table th,
    .students-table td {
        padding: 8px;
        border: 1px solid #ccc;
        font-size: 14px;
        text-align: center;
    }

    .students-table th {
        font-weight: bold;
        background-color: dodgerblue;
        color: white;
    }

    .students-table input[type="checkbox"] {
        margin-top: 5px;
        width: 20px;
        height: 20px;
    }

    .students-table img {
        width: 50px;
        height: 50px;
    }

    .absence-form {
        text-align: center;
        margin-bottom: 30px;
    }

    .absence-form input[type="datetime-local"] {
        margin-bottom: 10px;
    }

    .multiple-absence-form {
        text-align: center;
        margin-top: 30px;
    }

    /* Button styles */
    .absence-form button,
    .multiple-absence-form button {
        background-color: dodgerblue;
        color: white;
        border: none;
        padding: 8px 16px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 14px;
        cursor: pointer;
    }

    /* Button hover effect */
    .absence-form button:hover,
    .multiple-absence-form button:hover {
        background-color: #0066cc;
    }
</style>






<form action="${pageContext.request.contextPath}/enseignant/selectionnerNiveau" method="POST" class="absence-form">
    <div class="form-group">
        <label for="selectTypeSequence">Type de Séquence :</label>
        <select id="selectTypeSequence" name="typeSequence" class="form-control">
            <option value="">Sélectionner un type de séquence</option>
            <c:forEach var="typeSequence" items="${typesSequences}">
                <option value="${typeSequence.idTypeSeance}">${typeSequence.alias}-${typeSequence.intitule}</option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <label for="selectMatiere">Matière :</label>
        <select id="selectMatiere" name="nomMatiere" class="form-control">
            <option value="">Sélectionner une matière</option>
            <c:forEach var="matiere" items="${matieres}">
                <option value="${matiere.nom}">${matiere.nom}</option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <label for="idNiveau">Niveau:</label>
        <select id="idNiveau" name="idNiveau" required>
            <option value="">Sélectionner un niveau</option>
            <c:forEach var="niveau" items="${niveaux}">
                <option value="${niveau.idNiveau}">${niveau.alias} - ${niveau.titre}</option>
            </c:forEach>
        </select>
    </div>
    <button type="submit">Chercher les etudiants de ce niveau </button>



</form>


<h3>Liste des étudiants :</h3> <a href="${pageContext.request.contextPath}/absence/liste">Liste des absences</a>
<table class="students-table">
    <thead> <tr class="students-table-header">
    <tr>
        <th><strong>Selectionner l'etudiant</strong></th>
        <th><strong>Nom</strong></th>
        <th><strong>Prénom</strong></th>
        <th><strong>Email</strong></th>
        <th><strong>Téléphone</strong></th>
        <th><strong>Photo</strong></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="etudiant" items="${etudiants}">
        <tr>
            <td><input type="checkbox" name="absentStudents" value="${etudiant.idEtudiant}" /></td>
            <td>${etudiant.nom}</td>
            <td>${etudiant.prenom}</td>
            <td>${etudiant.email}</td>
            <td>${etudiant.telephone}</td>
            <td><img src="${etudiant.photo}" alt="Photo de l'étudiant" /></td>
            <td>
                <form action="/absence/enregistrer" method="post">
                    <input type="hidden" name="idEtudiant" value="${etudiant.idEtudiant}" />
                    <input type="hidden" id="nomMatiere" name="nomMatiere" value="${nomMatiere}" />
                    <input type="hidden" id="idTypeSeance" name="idTypeSeance" value="${idTypeSeance}" />
                    <input type="datetime-local" name="dateHeureDebutAbsence" />
                    <input type="datetime-local" name="dateHeureFinAbsence" />
                    <button type="submit">Marquer l'absence</button>
                </form>
<%--                <p>idEtudiant: ${etudiant.idEtudiant}</p>--%>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
















<form action="/absence/enregistrer-multiple"   class="multiple-absence-form" method="post">
    <div>
        <input type="hidden" name="absentStudents" /><br>
        <label for="dateHeureDebutAbsenceC">Date Commune de Debut d'Absence:</label>
        <input type="datetime-local" id="dateHeureDebutAbsenceC" name="dateHeureDebutAbsenceC" /><br>
        <label for="dateHeureFinAbsenceC">Date Commune de Fin d'Absence:</label>
        <input type="datetime-local" id="dateHeureFinAbsenceC" name="dateHeureFinAbsenceC" /><br>
    </div>
    <button type="submit">Enregistrer les absences sélectionnées</button>
</form>
