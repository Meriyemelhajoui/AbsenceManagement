<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
        <li class="nav-item"><a class="nav-link active"
                                aria-current="page"
                                href="${pageContext.request.contextPath}/enseignant/formulaire">Enseignant </a></li>

        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
                                         href="#" id="navbarScrollingDropdown" role="button"
                                         data-bs-toggle="dropdown" aria-expanded="true"> Absence Management </a>
            <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/enseignant/formulaire">Saisir Absence</a></li>
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/absence/liste">Voir Liste Absence </a></li>

            </ul></li>




        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
                                         href="#" id="navbarScrollingDropdown" role="button"
                                         data-bs-toggle="dropdown" aria-expanded="true">Account
            Management </a>
            <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">

                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/enseignant/formulaire">Modifier Infos Personnelles </a></li>

            </ul></li>

        <li class="nav-item">
            <form action="${pageContext.request.contextPath}/searchAbsenceById" method="GET">
            <input name="idEtudiant" class="form-control" type="text" placeholder="ID de l'étudiant">
            <button class="btn btn-primary" type="submit">Rechercher</button>
        </form>
        </li>

        <li class="nav-item"><f:form
                action="${pageContext.request.contextPath}/logout" method="POST">

            <button type="submit" class="btn btn-link">logout</button>
        </f:form></li>

    </ul>



</div>


