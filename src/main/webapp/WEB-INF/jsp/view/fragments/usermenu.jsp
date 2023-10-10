<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<div class="collapse navbar-collapse" id="navbarNav">
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link active"
								aria-current="page"
								href="${pageContext.request.contextPath}/user/showUserHome">Home</a></li>

		<li class="nav-item">
			<f:form action="${pageContext.request.contextPath}/logout" method="POST">
				<button type="submit" class="btn btn-link">Logout</button>
			</f:form>
		</li>
	</ul>
</div>

<style>
	/* Ajoutez votre style CSS ici */
	#navbarNav {
		background-color: #f8f9fa;
		padding: 10px;
	}

	.navbar-nav {
		margin: 0;
	}

	.nav-item {
		margin-right: 10px;
	}

	.nav-link {
		color: #333;
	}

	.btn-link {
		color: #333;
		text-decoration: underline;
	}

</style>
