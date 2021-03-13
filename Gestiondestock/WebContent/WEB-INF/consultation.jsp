<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container champsdesaisis">
		<div class="col-4"></div>
		<div class="col-4">
			<a
				href="<c:url value="/deconnexion"/>">Deconnexion</a>
		</div>
	</div>
	<table>
		<tr>
			<td>Id Produit</td>
			<td>Nom Produit</td>
			<td>Prix Produit</td>
		</tr>
		<c:forEach items="${requestScope.produits}" var="produit">
			<div class="news">
				<div class="produit id">
					<c:out value="${produit.id}" />
				</div>
				<div class="produit nom">
					<c:out value="${produit.nomproduit}" />
				</div>
				<div class="produit prix">
					<c:out value="${produit.prix}" />
				</div>
				<c:url var="urlemplacement" value="/consultation" scope="page">
					<c:param name="action" value="emplacment"></c:param>
					<c:param name="id" value="${produit.id }"></c:param>
				</c:url>
				<div class="list">
					<a href="${pageScope.urlemplacement}"><p>afficherEmplacement</p></a>
				</div>
			</div>
		</c:forEach>
	</table>
</body>
</html>