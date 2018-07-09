<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<title>Creer Voie</title>
		<sb:head/>
		<style type="text/css">
	        body {
	            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
	        }
    	</style>
	</head>
	
	<body>
		<%@include file="../_include/entete.jsp" %>
		<s:actionmessage/>
		<div class="container">
			<h3><s:text name="creerTopo.topo"/> <s:property value="#session.topo.nomTopo"/></h3>
			<h4><s:text name="creerTopo.site"/> <s:property value="#session.site.nomSite"/></h4>
			<h4><s:text name="creerTopo.secteur"/> <s:property value="#session.secteur.nomSecteur"/></h4>
			<h4><s:text name="creerTopo.titreVoie"/></h4>
				<s:form action="creer_voie" cssClass="form-horizontal" namespace="/jsp/utilisateur">
					<s:textfield name="voie.nomVoie" placeholder="nom de la voie" label="%{getText('form.nom')}" requiredLabel="false"/>
					<s:textfield name="voie.cotation" placeholder="cotation" label="%{getText('form.cotation')}" requiredLabel="true"/>
					<s:textfield name="voie.hauteur" placeholder="hauteur" label="%{getText('form.hauteur')}" requiredLabel="true"/>
					<s:textfield name="voie.nbLgueur" placeholder="nombre de longueurs" label="%{getText('form.nbLongueur')}" requiredLabel="true"/>
					<s:textfield name="voie.nbPoint" placeholder="nombre de points" label="%{getText('form.nbPoint')}" requiredLabel="true"/>
					<s:textfield name="voie.description" placeholder="description" label="%{getText('form.description')}" requiredLabel="false"/>
					<s:textfield name="secteur.nomSecteur" placeholder="#session.secteur.nomSecteur" label="%{getText('form.nomSecteur')}" requiredLabel="true"/>
					<s:textfield name="site.nomSite" placeholder="#session.site.nomSite" label="%{getText('form.nomSite')}" requiredLabel="true"/>
					<s:textfield name="topo.nomTopo" placeholder="#session.topo.nomTopo" label="%{getText('form.nomTopo')}" requiredLabel="true"/>
					<s:submit class="btn btn-default" value="%{getText('bouton.valider')}">
	      				<s:param name="nomVoie">${voie.nomVoie}</s:param>
	      				<s:param name="cotation">${voie.cotation}</s:param>
	      				<s:param name="hauteur">${voie.hauteur}</s:param>
	      				<s:param name="nbLgueur">${voie.nbLgueur}</s:param>
	      				<s:param name="nbPoint">${voie.nbPoint}</s:param>
	      				<s:param name="description">${voie.description}</s:param>
	      				<s:param name="nomSecteur">${session.secteur.nomSecteur}</s:param>
	      				<s:param name="nomSite">${session.site.nomSite}</s:param>
	      				<s:param name="nomTopo">${session.topo.nomTopo}</s:param>
	     			 </s:submit>
	     			 <s:token/>
				</s:form>	
				
				<s:a action="ajouter_secteur" namespace="/jsp/utilisateur">
					<s:submit class="btn btn-default" value="%{getText('bouton.ajouterSecteur')}">
						<s:param name="topo.nomTopo">${session.topo.nomTopo}</s:param>
					</s:submit>	
				</s:a>
				
				<s:a action="ajouter_site" namespace="/jsp/utilisateur">
					<s:submit class="btn btn-default" value="%{getText('bouton.ajouterSite')}">
						<s:param name="topo.nomTopo">${session.topo.nomTopo}</s:param>
					</s:submit>
				</s:a>
				
				<s:a action="ajouter_voie" namespace="/jsp/utilisateur">
					<s:submit class="btn btn-default" value="%{getText('bouton.ajouterVoie')}">
						<s:param name="topo.nomTopo">${session.topo.nomTopo}</s:param>
					</s:submit>
				</s:a>
				
				<s:a action="ajouter_image" namespace="/jsp/utilisateur">
					<s:submit class="btn btn-default" value="%{getText('bouton.ajouterImage')}">
						<s:param name="topo.nomTopo">${session.topo.nomTopo}</s:param>
					</s:submit>
				</s:a>				
				
				<s:a action="finaliser_topo" namespace="/jsp/utilisateur">
					<s:submit class="btn btn-default" value="%{getText('bouton.finaliser')}">
						<s:param name="nomTopo">${session.topo.nomTopo}</s:param>
					</s:submit>
				</s:a>		
		</div>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>	
	  	<%@include file="../_include/footer.jsp" %>		
	</body>
</html>