<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Emplacements</title>
</head>
<body>
	<div class="container champsdesaisis">
		<div class="col-4"></div>
		<div class="col-4">
			<a href="<c:url value="/deconnexion"/>">Deconnexion</a>
		</div>
	</div>
	<div class="container">
		<p>
			Id :
			<c:out value="${requestScope.produit.id }" />
		</p>
		<p>
			Nom :
			<c:out value="${requestScope.produit.nomproduit }" />
		</p>
		<p>
			Prix :
			<c:out value="${requestScope.produit.prix }" />
		</p>
	</div>
	<div class="zz">
		<c:forEach items="${requestScope.emplacements}" var="emplacement">
			<div class="news">
				<div class="produit id">
					<p>
						<c:out value="${emplacement.id}" />
					</p>
				</div>
				<div class="produit nom">
					<p>
						<c:out value="${emplacement.nom}" />
					</p>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>