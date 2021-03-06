<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="/warez-escalade-webapp/jsp/style.css" />	
		<title>Modifier Voie</title>
		<!--<sb:head includeScripts="false" includeStyles="true"/>-->
	</head>
	
	<body>
		<%@include file="../_include/entete.jsp" %>
		<div id="blocPge">
			<s:actionmessage/>
			<div class="container text-center">
				<h1 id="titre"><s:text name="modifierTopo.topo"/> <s:property value="topo.nomTopo"/></h1>
				<h4 id="titre"><s:text name="modifierTopo.site"/> <s:property value="site.nomSite"/></h4>
				<h4 id="titre"><s:text name="modifierTopo.secteur"/> <s:property value="secteur.nomSecteur"/></h4>
				<h4 id="titre"><s:text name="modifierTopo.titreVoie"/> <s:property value="voie.nomVoie"/></h4>
					<s:form action="modifier_voie" cssClass="form-vertical" namespace="/jsp/utilisateur">
						<s:textfield name="voie.nomVoie" placeholder="nom de la voie" label="%{getText('form.nom')}" requiredLabel="false"/>
						<s:textfield name="voie.cotation" placeholder="cotation" label="%{getText('form.cotation')}" requiredLabel="true"/>
						<s:textfield name="voie.hauteur" placeholder="hauteur" label="%{getText('form.hauteur')}" requiredLabel="true"/>
						<s:textfield name="voie.nbLgueur" placeholder="nombre de longueurs" label="%{getText('form.nbLongueur')}" requiredLabel="true"/>
						<s:textfield name="voie.nbPoint" placeholder="nombre de points" label="%{getText('form.nbPoint')}" requiredLabel="true"/>
						<s:textfield name="voie.description" placeholder="description" label="%{getText('form.description')}" requiredLabel="false"/>
						<s:textfield name="secteur.nomSecteur" placeholder="#secteur.nomSecteur" label="%{getText('form.nomSecteur')}" requiredLabel="true"/>
						<s:textfield name="site.nomSite" placeholder="#site.nomSite" label="%{getText('form.nomSite')}" requiredLabel="true"/>
						<s:textfield name="topo.nomTopo" placeholder="#topo.nomTopo" label="%{getText('form.nomTopo')}" requiredLabel="true"/>
						<s:submit class="btn btn-default" value="%{getText('bouton.valider')}">
		      				<s:param name="nomVoie">${voie.nomVoie}</s:param>
		      				<s:param name="cotation">${voie.cotation}</s:param>
		      				<s:param name="hauteur">${voie.hauteur}</s:param>
		      				<s:param name="nbLgueur">${voie.nbLgueur}</s:param>
		      				<s:param name="nbPoint">${voie.nbPoint}</s:param>
		      				<s:param name="description">${voie.description}</s:param>
		      				<s:param name="nomSecteur">${#secteur.nomSecteur}</s:param>
		      				<s:param name="nomSite">${#site.nomSite}</s:param>
		      				<s:param name="nomTopo">${#topo.nomTopo}</s:param>
		     			 </s:submit>
		     			 <s:token/>
					</s:form>	
			</div>
		</div>
		<%@include file="../_include/footer.jsp" %>	
		
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>	
	  		
	</body>
</html>