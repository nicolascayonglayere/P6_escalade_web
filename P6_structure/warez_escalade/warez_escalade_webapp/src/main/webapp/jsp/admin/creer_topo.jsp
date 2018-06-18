<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<title>Creer Topo</title>
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
			<h3><s:text name="creerTopo.titre"/></h3>
			<s:form action="creer_topo" cssClass="form-horizontal" namespace="/jsp/utilisateur">
				<s:textfield name="topo.nom" placeholder="nom du topo" label="%{getText('form.nomTopo')}" requiredLabel="true"/>
				<s:textfield name="topo.nbreEx" placeholder="nombre d'exemplaire" label="%{getText('form.exemplaire')}" requiredLabel="true"/>
				<s:textfield name="topo.description" placeholder="description" label="%{getText('form.description')}" requiredLabel="false"/>
				<s:textfield name="topo.longitude" placeholder="longitude" label="%{getText('form.longitude')}" requiredLabel="true"/>
				<s:textfield name="topo.latitude" placeholder="latitude" label="%{getText('form.latitude')}" requiredLabel="true"/>
				<s:textfield name="topo.nombreSite" placeholder="nombre de sites" label="%{getText('form.nbSite')}" requiredLabel="false"/>
				<s:textfield name="topo.nombreSecteur" placeholder="nombre de secteurs" label="%{getText('form.nbSecteur')}" requiredLabel="false"/>
				<s:textfield name="topo.nombreVoie" placeholder="nombre de voies" label="%{getText('form.nbVoie')}" requiredLabel="false"/>				
				<s:submit class="btn btn-default" value="%{getText('bouton.valider')}">
      				<s:param name="nom">${topo.nom}</s:param>
      				<s:param name="nbreEx">${topo.nbreEx}</s:param>
      				<s:param name="description">${topo.description}</s:param>
      				<s:param name="longitude">${topo.longitude}</s:param>
      				<s:param name="latitude">${topo.latitude}</s:param>
      				<s:param name="nombreSite">${topo.nombreSite}</s:param>
      				<s:param name="nombreSecteur">${topo.nombreSecteur}</s:param>
      				<s:param name="nombreVoie">${topo.nombreVoie}</s:param>      				
     			 </s:submit>
     			 <s:token/>
			</s:form>

			<s:iterator value="(1).{#topo.nombreSite}">
				<s:form action="creer_site" cssClass="form-horizontal" namespace="/jsp/utilisateur">
					<s:textfield name="site.nom" placeholder="nom du site" label="%{getText('form.nomSite')}" requiredLabel="false"/>
					<s:textfield name="site.descritpion" placeholder="description" label="%{getText('form.description')}" requiredLabel="false"/>
					<s:textfield name="topo.nom" placeholder="nom du topo" label="%{getText('form.nomTopo')}" requiredLabel="true"/>
					<s:submit class="btn btn-default" value="%{getText('bouton.valider')}">
	      				<s:param name="nom">${site.nom}</s:param>
	      				<s:param name="description">${site.descritpion}</s:param>
	      				<s:param name="nom">${topo.nom}</s:param>
	     			 </s:submit>
	     			 <s:token/>
				</s:form>				
			</s:iterator>
	
			<s:iterator value="(1).{#topo.nombreSecteur}">
				<s:form action="creer_secteur" cssClass="form-horizontal" namespace="/jsp/utilisateur">
					<s:textfield name="secteur.nom" placeholder="nom du secteur" label="%{getText('form.nomSecteur')}" requiredLabel="false"/>
					<s:textfield name="secteur.descritpion" placeholder="description" label="%{getText('form.description')}" requiredLabel="false"/>
					<s:textfield name="site.nom" placeholder="nom du site" label="%{getText('form.nomSite')}" requiredLabel="true"/>
					<s:textfield name="topo.nom" placeholder="nom du topo" label="%{getText('form.nomSecteur')}" requiredLabel="true"/>
					<s:submit class="btn btn-default" value="%{getText('bouton.valider')}">
	      				<s:param name="nom">${secteur.nom}</s:param>
	      				<s:param name="description">${secteur.descritpion}</s:param>
	      				<s:param name="nomSite">${site.nom}</s:param>
	      				<s:param name="nomTopo">${topo.nom}</s:param>
					</s:submit>
	     			 <s:token/>
				</s:form>
			</s:iterator>			
			
			<s:iterator value="(1).{#topo.nombreVoie}">			
				<s:form action="creer_voie" cssClass="form-horizontal" namespace="/jsp/utilisateur">
					<s:textfield name="voie.nom" placeholder="nom de la voie" label="%{getText('form.nom')}" requiredLabel="false"/>
					<s:textfield name="voie.cotation" placeholder="cotation" label="%{getText('form.cotation')}" requiredLabel="true"/>
					<s:textfield name="voie.hauteur" placeholder="hauteur" label="%{getText('form.hauteur')}" requiredLabel="true"/>
					<s:textfield name="voie.nbLongueur" placeholder="nombre de longueurs" label="%{getText('form.nbLongueur')}" requiredLabel="true"/>
					<s:textfield name="voie.nbPoint" placeholder="nombre de points" label="%{getText('form.nbPoint')}" requiredLabel="true"/>
					<s:textfield name="voie.descritpion" placeholder="description" label="%{getText('form.description')}" requiredLabel="false"/>
					<s:textfield name="secteur.nom" placeholder="nom du secteur" label="%{getText('form.nomSecteur')}" requiredLabel="true"/>
					<s:textfield name="site.nom" placeholder="nom du site" label="%{getText('form.nomSite')}" requiredLabel="true"/>
					<s:textfield name="topo.nom" placeholder="nom du topo" label="%{getText('form.nomTopo')}" requiredLabel="true"/>
					<s:submit class="btn btn-default" value="%{getText('bouton.valider')}">
	      				<s:param name="nom">${voie.nom}</s:param>
	      				<s:param name="cotation">${voie.cotation}</s:param>
	      				<s:param name="hauteur">${voie.hauteur}</s:param>
	      				<s:param name="nbLongueur">${voie.nbLongueur}</s:param>
	      				<s:param name="nbPoint">${voie.nbPoint}</s:param>
	      				<s:param name="description">${voie.descritpion}</s:param>
	      				<s:param name="nomSecteur">${secteur.nom}</s:param>
	      				<s:param name="nomSite">${site.nom}</s:param>
	      				<s:param name="nomTopo">${topo.nom}</s:param>
	     			 </s:submit>
	     			 <s:token/>
				</s:form>
			</s:iterator>							
		</div>
		
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>	
	  	<%@include file="../_include/footer.jsp" %>		
	</body>
</html>