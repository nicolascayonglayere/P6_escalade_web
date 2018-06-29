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
		<%@include file="../_include/entete.jsp" %>	
		<div class="container">

			<h2><s:text name="compteUser.titre"/><s:property value="#session.utilisateur.pseudo"/></h2>
			<h3><s:text name="compteUser.bonjour"/><s:property value="utilisateur.nom"/> <s:property value="utilisateur.prenom"/></h3>
			<h4><s:text name="compteUser.role"/><s:property value="utilisateur.role"/></h4>
			<s:actionmessage/>			
			<!-- pour les modérateurs, une liste des commentaires à valider -->
			<s:if test="%{utilisateur.role == 'moderateur'}">
				<s:iterator value="listCommentaire" var="commentaire">
					<ul>
						<li>
							<i class="fas fa-user"> <s:property value="#commentaire.auteur.pseudo"/></i>
							<s:property value="#commentaire.message"/>
							<s:a action="valider_commentaire" namespace="/jsp/utilisateur">
								<s:submit class="btn btn-default" value="%{getText('bouton.valider')}">
				    				<s:param name="commentaire" value="#commentaire.message"/>
				    				<s:param name="pseudo" value="#commentaire.auteur.pseudo"/>
				    			</s:submit>
				    		</s:a>
							<s:a action="rejeter_commentaire" namespace="/jsp/utilisateur">
								<s:submit class="btn btn-default" value="%{getText('bouton.rejeter')}">
				    				<s:param name="commentaire" value="#commentaire.message"/>
				    				<s:param name="pseudo" value="#commentaire.auteur.pseudo"/>
				    			</s:submit>
				    		</s:a>				    		
						</li>
					</ul>
				</s:iterator>
			</s:if>
			<!-- pour les admin, des liens vers les actions réservées -->
			<s:elseif test="%{utilisateur.role =='administrateur'}">
				<h5><s:text name="compteUser.ban"/></h5>
				<s:a action="go_ban" namespace="/jsp/utilisateur">
					<s:text name="compteUser.ban"/>
	    		</s:a>
	    		<h5><s:text name="compteUser.topo"/></h5>
	    		<s:a action="go_creerTopo" namespace="/jsp/utilisateur">
	    			<s:text name="compteUser.creerTopo"/>
	    		</s:a>
	    		<s:a action="go_chercherTopo" namespace="/jsp/utilisateur">
	    			<s:text name="compteUser.modifTopo"/>
	    		</s:a>
	    		<s:a action="go_chercherTopo" namespace="/jsp/utilisateur">
	    			<s:text name="compteUser.supprTopo"/>
	    		</s:a>				
			</s:elseif>
			
			<!-- un tab recapitulatif des topos emrpuntés (historique tot ou partiel ?)-->
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th><s:text name="compteUser.nom"/></th>
						<th><s:text name="compteUser.dateEmprunt"/></th>
						<th><s:text name="compteUser.dateRetour"/></th>
						<th><s:text name="compteUser.retour"/></th>
					</tr>
				</thead>
					<s:iterator value="listTopoEmprunt" var="topoEmprunt">
						<tr>
					    	<td style="text-align:left;"><s:property value="#topoEmprunt.nom"/></td>
					    	<td style="text-align:right;"><s:property value="#topoEmprunt.dateEmprunt" /></td>
					    	<td style="text-align:right;"><s:property value="#topoEmprunt.dateRetour" /></td>
					    	<td>
					    		<s:a action="retour_topo" namespace="/jsp/utilisateur">
					    			<s:param name="nom" value="#topoEmprunt.nom"/>
					    			<s:submit class="btn btn-default" value="%{getText('bouton.retour')}"/>
					    		</s:a>	
					    	</td>
					   	</tr>
				 	</s:iterator>
			</table>		
		</div>

		
		<!-- un formulaire pour modifier ses param pseudo et mdp et coordonnee -->
		<div class="container" style="border-color: gray; border-style: solid; border-width: medium">
			<h5><s:text name="compteUser.modifier"/></h5>
			<s:form action="modifier_user" cssClass="form-vertical" namespace="/jsp/utilisateur">
				<s:textfield name="utilisateur.pseudo" placeholder="pseudo" label="%{getText('form.pseudo')}" requiredLabel="true"/>
				<s:textfield name="utilisateur.password" placeholder="password" label="%{getText('form.mdp')}" requiredLabel="true"/>
				<s:textfield name="coordonneeUtilisateur.email" placeholder="email" label="%{getText('form.email')}" requiredLabel="true"/>
				<s:textfield name="coordonneeUtilisateur.adresse" placeholder="adresse" label="%{getText('form.adresse')}" requiredLabel="true"/>
				<s:submit class="btn btn-default" value="%{getText('bouton.modifier')}">
					<s:param name="pseudo">${utilisateur.pseudo}</s:param>
					<s:param name="password">${utilisateur.password}</s:param>
					<s:param name="email">${coordonneeUtilisateur.email}</s:param>
					<s:param name="adresse">${coordonneeUtilisateur.adresse}</s:param>
				</s:submit>
			</s:form>
		</div>

		</br>
	
		<div class="container" style="border-color: gray; border-style: solid; border-width: medium">
			<h5><s:text name="compteUser.supprimer"/></h5>
			<p><s:text name="compteUser.supprMessage"/> 
				<s:a action = "supprimer_compte" namespace="/jsp/utilisateur">
					<s:submit class="btn btn-default" value="%{getText('bouton.supprimer')}"/>			
				</s:a>
			</p>		
		</div>
			
		
	
					
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	  	<%@include file="../_include/footer.jsp" %>
	</body>
</html>