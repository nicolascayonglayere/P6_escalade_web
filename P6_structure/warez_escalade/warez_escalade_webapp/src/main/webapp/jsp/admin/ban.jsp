<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<title>BAN</title>
		<sb:head/>
		<style type="text/css">
	        body {
	            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
	        }
    	</style>
	</head>

	<body>
		<%@include file="../_include/entete.jsp" %>
			
		<s:form id="rechercheUtilisateur" action="recherche_utilisateur" theme="bootstrap" cssClass="form-horizontal">
			<s:textfield name="utilisateur.pseudo" placeholder="pseudo" label="pseudo" requiredLabel="true"/>
			<input class="btn btn-default" type="submit" value="RECHERCHER">
		  		<s:param name="pseudo">${utilisateur.pseudo}</s:param>
		 	 </input>
		</s:form>		
			
		<ul>
			<s:iterator value="listUtilisateur" var="utilisateur">
				<li><s:property value="#utilisateur.pseudo"/> <s:property value="#utilisateur.nom"/> <s:property value="#utilisateur.prenom"/></li>
				<ul>
				<s:iterator value="listTopoEmprunt" var="topoEmprunt">
					<li><s:property value="#topoEmprunt.nom"/> <s:property value="#topoEmprunt.date"/></li>
				</s:iterator>
				</ul>
			</s:iterator>
			<s:a action="bannir">
				<input class="btn btn-default" type="submit" value="BANNIR">
			  		<s:param name="pseudo">${utilisateur.pseudo}</s:param>
			 	 </input>			
			</s:a>
		</ul>
		
		<%@include file="../_include/footer.jsp" %>			
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>		
	</body>
</html>