<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>

<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
    
<title>Hello, world!</title>   
</head>
<style>
.champsdesaisis {
	margin-bottom: 20px;
	margin-right: 180px;
	margin-left: 180px;
}

.champsdesaisis button {
	border: none;
	font-size: 20px;
	border-radius: 12px;
	padding-bottom: 15px;
	padding-top: 15px;
	padding-left: 80px;
	padding-right: 80px;
}
</style>
  
<body>
	<div class="container formulaire">
		<form method="POST" action="">

			<div class="row champsdesaisis">
				<div class="col">
					<label for="nom">Nom</label>
				</div>
				<div class="col">
					<input class="form-control" id="nom" type="text" name="nom" value="${requestScope.personne.nom}" />
					<p><c:out value="${requestScope.errors.errornom }"/></p>
				</div>
			</div>
			<div class="row champsdesaisis">
				<div class="col">
					<label for="prenom">Prenom</label>
				</div>
				<div class="col">
					<input class="form-control" id="prenom" type="text" name="prenom" value="${requestScope.personne.prenom}" />
					<p><c:out value="${requestScope.errors.errorprenom }"/></p>
				</div>
			</div>
			<div class="row champsdesaisis">
				<div class="col">
					<label for="date">Date</label>
				</div>
				<div class="col">
					<input class="form-control" id="date" type="date" name="date" value="${requestScope.personne.date}" />
					<p><c:out value="${requestScope.errors.errordate }"/></p>
				</div>
			</div>
			<div class="row champsdesaisis">
				<div class="col">
					<label for="email">Email</label>
				</div>
				<div class="col">
				
					<input class="form-control" id="email" type="email" name="email" value="<c:out value="${requestScope.personne.username }"/>" />
					<p><c:out value="${requestScope.errors.erroremail }"/></p>
				</div>
			</div>
			<div class="row champsdesaisis">
				<div class="col">
					<label for="password">Password</label>
				</div>
				<div class="col">
					<input class="form-control" id="password" name="password" type="password" value="" />
					<p><c:out value="${requestScope.errors.errorpass }"/></p>
				</div>
			</div>
			<div class="row champsdesaisis">
				<div class="col-4"></div>
				<div class="col-4">
					<button class="btn-success">Inscrire</button>
				</div>
			</div>
			<div class="row champsdesaisis">
				<div class="col-4"></div>
				<div class="col-4">
					<p><c:out value="${requestScope.errors.error }"/></p>
				</div>
			</div>
			
			<div class="row champsdesaisis">
				<div class="col-4"></div>
				<div class="col-4">
					<a href="<c:url value="/connexion"/>">Se connecter</a>
				</div>
			</div>

		</form>
	</div>
	    
	<!-- Optional JavaScript -->

	    
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->

	    
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>

	    
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>

	    
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>

	  
</body>

</html>

