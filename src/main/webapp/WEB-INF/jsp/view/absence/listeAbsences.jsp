<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>




    <div class="container">
        <jsp:include page="../fragments/adminHeader.jsp" />

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">

                <jsp:include page="../fragments/Absentmenu.jsp" />

            </div>
        </nav>
    </div>







<%--    <jsp:include page="../fragments/usermenu.jsp" />--%>


    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        h1 {
            text-align: center;
        }
    </style>
</head>
<body>
<!-- Formulaire de recherche d'étudiant par ID -->
<form action="${pageContext.request.contextPath}/absence/liste" method="GET">
<%--    <input name="idEtudiant" class="form-control" type="text" placeholder="ID de l'étudiant">--%>
<%--    <button class="btn btn-primary" type="submit">Rechercher</button>--%>
</form>

<!-- Tableau des absences -->
<table>
    <!-- Entêtes du tableau -->
    <h1>Liste des absences</h1>
    <tr>
        <th>ID Absence</th>
        <th>ID Etudiant</th>
        <th>Etudiant</th>
        <th>Date Heure Début d'absence</th>
        <th>Date Heure Fin d'absence</th>
        <th>ID TypeSeance</th>
        <th>Type Saisie</th>
        <th>ID Matiere</th>
        <th>Nom Matiere</th>
        <th>Action</th>
    </tr>

    <!-- Lignes du tableau -->
    <c:forEach var="absence" items="${absences}">
        <tr>
            <td>${absence.idAbsence}</td>
            <td>${absence.inscription.idInscription}</td>
            <td>${absence.inscription.etudiant.nom} ${absence.inscription.etudiant.prenom}</td>
            <td>${absence.dateHeureDebutAbsence}</td>
            <td>${absence.dateHeureFinAbsence}</td>
            <td>${absence.typeSeance.idTypeSeance}</td>
            <td>${absence.typeSeance.alias}</td>
            <td>${absence.matiere.idMatiere}</td>
            <td>${absence.matiere.nom}</td>
            <td>
                <!-- Lien ou bouton "Voir fiche d'absence" -->
                <a href="/ficheAbsence?idInscription=${absence.inscription.idInscription}">
                    Voir fiche d'absence
                </a>
            </td>

            <td>
                <form action="/supprimerAbsence" method="POST" onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer cette absence ?')">
                    <input type="hidden" name="idAbsence" value="${absence.idAbsence}" />
                    <button type="submit" class="btn btn-danger">Supprimer</button>
                </form>
            </td>

        </tr>


    </c:forEach>
</table>



