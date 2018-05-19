<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">		
		<title>Inscription</title>
	</head>

	<body>
		<%@include file="_include/entete.jsp" %>
		<div class="container-fluid text-center">
			<h3>INSCRIPTION</h3>
			<s:form action="creer_user">
				<s:textfield name="nom" placeholder="nom" label="nom" requiredLabel="true"/>
				<s:textfield name="prenom" placeholder="prenom" label="prenom" requiredLabel="true"/>
				<s:textfield name="pseudo" placeholder="pseudo" label="pseudo" requiredLabel="true"/>
				<s:textfield name="password" placeholder="password" label="password" requiredLabel="true"/>
				<s:textfield name="email" placeholder="email" label="email" requiredLabel="true"/>
				<input class="btn btn-default" type="submit" value="VALIDER">
      				<s:param name="nom">${nom}</s:param>
      				<s:param name="prenom">${prenom}</s:param>
      				<s:param name="pseudo">${pseudo}</s:param>
      				<s:param name="password">${password}</s:param>
      				<s:param name="email">${email}</s:param>
     			 </input>
			
			</s:form>
		
		</div>
		
		
		
		<%@include file="_include/footer.jsp" %>
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	</body>
</html>