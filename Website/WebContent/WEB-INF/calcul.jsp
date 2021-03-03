<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value="/calcul"/>" method="POST">
		<label for="saisiid">Saisi</label>
		<input type="text" name="saisi" value=""/>
		<input  type="submit" value="valider"/>
	</form>
	<p>${requestScope.calcul.operation()}</p>
	<div style="color:red;">
		${requestScope.erreurs.premier }
		${requestScope.erreurs.deusieme }
		${requestScope.erreurs.operator }
		${requestScope.erreurs.error }
		
	</div>
	
</body>
</html>