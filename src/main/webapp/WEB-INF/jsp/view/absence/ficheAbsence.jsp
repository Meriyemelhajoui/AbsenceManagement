<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2>Fiche d'absence de ${etudiant.nom} ${etudiant.prenom}</h2>
<style>
    body {
        font-family: Arial, sans-serif;
    }

    h2 {
        text-align: center;
    }

    table {
        width: 100%;
        border-collapse: collapse;
    }

    th, td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    th {
        background-color: #f2f2f2;
    }
</style>


<table>
    <!-- Afficher les informations de chaque absence -->
    <c:forEach var="absence" items="${absences}">
        <tr>
            <th>Matière</th>
            <th>Type de séance</th>
            <th>Jour d'absence</th>
            <th>Heure de début</th>
            <th>Heure de fin</th>
        </tr>
        <tr>
            <td>${absence.matiere.nom}</td>
            <td>${absence.typeSaisie}</td>
            <td>${absence.getFormattedDate()}</td>
            <td>${absence.getFormattedStartTime()}</td>
            <td>${absence.getFormattedEndTime()}</td>
        </tr>
    </c:forEach>
</table>
