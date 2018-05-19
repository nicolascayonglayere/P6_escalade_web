<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">		
		<title>Présentation</title>
	</head>

	<body>
		<%@include file="_include/entete.jsp" %>
		<div class="container-fluid">
			<h2>QUI SOMMES-NOUS ?</h2>
			<section>
				<h3>Un groupe de partage</h3>
				<p>Passionné d'escalade en plein air, nous avons décidé de créer ce site de partage de topo.</br></p>
			</section>
			<section>	
				<h3>L'équipe</h3>
				<p>Les administrateurs</p>
				<ul>
					<!-- la liste des admins avec un s:iterator -->
					<li>un admin</li>
				</ul>
				<p>Les adminsitrateurs ont tous les droits !</br></p>
				
				<p>Les modérateurs</p>
				<ul>
					<!-- la liste des modo avec un s:iterator -->
					<li>un modérateur</li>
				</ul>
				<p>Les modérateurs controllent les commentaires et peuvent ajouter des topos dans la base de donnée</p>
				
			</section>		
		</div>

		
		
		
		
		
		<%@include file="_include/footer.jsp" %>
	</body>
</html>