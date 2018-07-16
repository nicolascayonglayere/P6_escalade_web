<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="../style.css" />
		<title>Mon compte</title>
		<sb:head/>		
	</head>
	
	<body>
		<div id="blocPge">
			<%@include file="../_include/entete.jsp" %>	
			<div class="container" >
				
				<div class="row">
					<div class="col-lg">
						<h2 id="titre"><s:text name="compteUser.titre"/><s:property value="#session.utilisateur.pseudo"/></h2>
						<h3 id="titre"><s:text name="compteUser.bonjour"/><s:property value="utilisateur.nom"/> <s:property value="utilisateur.prenom"/></h3>
						<h4 id="titre"><s:text name="compteUser.role"/><s:property value="utilisateur.role"/></h4>
						<s:actionmessage/>
					</div>	
					
					<div>		
					<!-- pour les modérateurs, une liste des commentaires à valider -->
					<s:if test="%{utilisateur.role == 'moderateur'}">
						<s:iterator value="listCommentaire" var="commentaireTopo">
							<ul>
								<li>
									<i class="fas fa-user"> <s:property value="#commentaireTopo.auteur.pseudo"/></i>
									<s:property value="#commentaireTopo.message"/>
									<s:property value="#commentaireTopo.topo.nomTopo"/>
									<s:a action="valider_commentaire" namespace="/jsp/utilisateur">
										<s:param name="nomTopo" value="#commentaireTopo.topo.nomTopo"/>
										<s:param name="pseudo" value="#commentaireTopo.auteur.pseudo"/>
										<s:param name="message" value="#commentaireTopo.message"/>
										<s:submit class="btn btn-default" value="%{getText('bouton.valider')}"/>
						    		</s:a>
									<s:a action="rejeter_commentaire" namespace="/jsp/utilisateur">
										<s:param name="nomTopo" value="#commentaireTopo.topo.nomTopo"/>
						    			<s:param name="message" value="#commentaireTopo.message"/>
						    			<s:param name="pseudo" value="#commentaireTopo.auteur.pseudo"/>								
										<s:submit class="btn btn-default" value="%{getText('bouton.rejeter')}"/>
						    		</s:a>				    		
								</li>
							</ul>
						</s:iterator>
					</s:if>
					</div>
					<!-- pour les admin, des liens vers les actions réservées -->
					<s:elseif test="%{utilisateur.role =='administrateur'}">
						<div class="navbar navbar-light fixed-top col-lg-2" id="menuTop">
							<div class="collapse navbar-collapse"  >
								<ul class="navbar-nav mr-auto">
									<h4 id="titre" class="dropdown-header"><s:text name="%{getText('compteUser.utilisateur')}"/></h4>
									<li class="nav-item active">
										<s:a action="go_modifUser" namespace="/jsp/utilisateur">
											<s:text name="compteUser.modifUser"/>
							    		</s:a>							
									</li>							
									<li class="nav-item active">
										<s:a action="go_ban" namespace="/jsp/utilisateur">
											<s:text name="compteUser.ban"/>
							    		</s:a>							
									</li>
									<!-- Un séparateur -->
									<div class="dropdown-divider"></div>
									<h4 id="titre" class="dropdown-header"><s:text name="%{getText('compteUser.topo')}"/></h4>
									<li class="nav-item active">
							    		<s:a action="go_creerTopo" namespace="/jsp/utilisateur">
							    			<s:text name="compteUser.creerTopo"/>
							    		</s:a>							
									</li>
									<li class="nav-item active">
							    		<s:a action="go_chercherTopo" namespace="/jsp/utilisateur">
							    			<s:text name="compteUser.modifTopo"/>
							    		</s:a>							
									</li>
									<li class="nav-item active">
							    		<s:a action="go_chercherTopo" namespace="/jsp/utilisateur">
							    			<s:text name="compteUser.supprTopo"/>
							    		</s:a>							
									</li>							
			    				</ul>			
							</div>
						</div>
				</div>
				<div class="row">	
						<div class="col-lg-12">
							<!-- un recap des topo en cours de creation -->
							<h4 id="titre"><s:text name="%{getText('compteUser.creation')}"/></h4>
							<table class="table table-bordered table-striped">
									<thead>
										<tr class="table-primary">
											<th><s:text name="compteUser.nom"/></th>
											<th><s:text name="compteUser.topo"/></th>
										</tr>
									</thead>
									<s:iterator value="listTopoConstr" var="topo">
										<tr>
									    	<td style="text-align:left;"><s:property value="#topo.auteur.pseudo"/></td>
									    	<td style="text-align:right;"><s:property value="#topo.nomTopo" /></td>
									    	<td>
									    		<s:a action="recap_topo" namespace="/jsp/utilisateur">
									    			<!--<s:param name="nomTopo" value="#topo.nomTopo"/>-->
									    			<s:submit class="btn btn-default" value="%{getText('bouton.modifier')}">
									    				<s:param name="nomTopo" value="#topo.nomTopo"/>
									    			</s:submit>
									    		</s:a>	
									    	</td>
									   	</tr>
								 	</s:iterator>
							</table>
						</div>				
					</s:elseif>
				</div>
				
				<div>	
					<h4 id="titre"><s:text name="%{getText('compteUser.emprunt')}"/></h4>
					
					<!-- un tab recapitulatif des topos emrpuntés (historique tot ou partiel ?)-->
					<table class="table table-bordered table-striped">
						<thead >
							<tr class="table-primary">
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
				<div class="col-lg-12" style="border-color: gray; border-style: solid; border-width: medium">
					<h4 id="titre"><s:text name="compteUser.modifier"/></h4>
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
			
				<div class="col-lg-12" style="border-color: gray; border-style: solid; border-width: medium">
					<h5 id="titre"><s:text name="compteUser.supprimer"/></h5>
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
			</div>	
		</div>	  	
	</body>
</html>