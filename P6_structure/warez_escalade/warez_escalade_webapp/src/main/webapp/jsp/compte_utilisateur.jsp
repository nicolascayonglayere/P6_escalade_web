<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		
		<title>Mon compte</title>
		<sb:head/>
		<style type="text/css">
	        body {
	            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
	        }
    	</style>		
	</head>
	
	<body>
		<%@include file="_include/entete.jsp" %>	
		<h2>COMPTE UTILISATEUR : <s:property value="#session.utilisateur.pseudo"/></h2>
		<h3>Bonjour <s:property value="utilisateur.nom"/> <s:property value="utilisateur.prenom"/></h3>
		<h4>role : <s:property value="utilisateur.role"/></h4>
		
		<!-- un tab recapitulatif des topos emrpuntés (historique tot ou partiel ?)-->
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>Nom</th>
					<th>Date d'emprunt</th>
					<th>Date de retour</th>
					<th>Retour</th>
				</tr>
			</thead>
				<s:iterator value="listTopoEmprunt" var="topoEmprunt">
					<tr>
				    	<td style="text-align:left;"><s:property value="#topoEmprunt.nom"/></td>
				    	<td style="text-align:right;"><s:property value="#topoEmprunt.dateEmprunt" /></td>
				    	<td style="text-align:right;"><s:property value="#topoEmprunt.dateRetour" /></td>
				    	<td>
				    		<s:a action="retour_topo">
				    			<input type="submit" class="cssButton btn btn-primary" value="RETOUR">
				    				<s:param name="nom" value="#topoEmprunt.nom"/>
				    				<s:param name="pseudo" value="#session.utilisateur.pseudo"/>
				    			</input>
				    		</s:a>	
				    	</td>
				   	</tr>
			 	</s:iterator>
		</table>
		
		<!-- un formulaire pour modifier ses param pseudo et mdp et coordonnee -->
		<h5>MODIFIER MON COMPTE</h5>
		<s:form action="modifier_user" cssClass="form-vertical">
			<s:textfield name="utilisateur.pseudo" placeholder="pseudo" label="pseudo" requiredLabel="true"/>
			<s:textfield name="utilisateur.password" placeholder="password" label="password" requiredLabel="true"/>
			<s:textfield name="coordonneeUtilisateur.email" placeholder="email" label="email" requiredLabel="true"/>
			<s:textfield name="coordonneeUtilisateur.adresse" placeholder="adresse" label="adresse" requiredLabel="true"/>
			<input class="btn btn-default" type="submit" value="MODIFIER">
				<s:param name="pseudo">${utilisateur.pseudo}</s:param>
				<s:param name="password">${utilisateur.password}</s:param>
				<s:param name="email">${coordonneeUtilisateur.email}</s:param>
				<s:param name="adresse">${coordonneeUtilisateur.adresse}</s:param>
			</input>			
		</s:form>	
		
		<h5>SUPPRIMER MON COMPTE</h5>
		<p>Vous souhaitez supprimer votre compte, cliquez sur le bouton  ---> 
			<s:a action = "supprimer_compte">			
				<input class="btn btn-default" type="submit" value="SUPPRIMER">
					<s:param name="pseudo" value="#session.utilisateur.pseudo"/>
				</input>
			</s:a>
		</p>			
		
	
		<%@include file="_include/footer.jsp" %>			
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	</body>
</html>