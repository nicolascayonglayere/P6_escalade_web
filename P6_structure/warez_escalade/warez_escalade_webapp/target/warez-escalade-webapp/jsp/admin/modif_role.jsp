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
		<title>Modifier le role</title>
		<!--<sb:head includeScripts="false" includeStyles="true"/>-->
	</head>

	<body>
		<%@include file="../_include/entete.jsp" %>
		<div id="blocPge">
			<div class="container">
				<h1 id="titre"><s:text name="modifRole.titre"/></h1>
				<s:form id="rechercheUtilisateur" action="recherche_utilisateurModif" cssClass="form-vertical" namespace="/jsp/utilisateur">
					<s:textfield name="utilisateur.pseudo" placeholder="pseudo" label="%{getText('form.pseudo')}" requiredLabel="true"/>
					<s:submit class="btn btn-default" value="%{getText('bouton.rechercher')}">
				  		<div id="idPseudo"><s:param name="pseudo">${utilisateur.pseudo}</s:param></div>
				 	 </s:submit>
				</s:form>		
				
				<s:if test="%{listUtilisateur}">
				<s:form action="modif_role" cssClass="form-vertical" namespace="/jsp/utilisateur">
					<ul id="formulaireRole">
						<s:iterator value="listUtilisateur" var="utilisateur">
							<li>
								<s:checkbox name="checkMe" fieldValue="%{#utilisateur.pseudo}" label="%{#utilisateur.pseudo+' '+#utilisateur.nom+' '+#utilisateur.prenom}"/>
							</li>
							<ul>
							<s:iterator value="%{#utilisateur.getlistTopoEmprunt}" var="topoEmprunt">
								<li>
									<s:property value="#topoEmprunt.nom"/> <s:property value="#topoEmprunt.dateRetour"/>
								</li>
							</s:iterator>
							</ul>
						</s:iterator>
					</ul>
					<!-- une selectBox pour definir le nouveau role -->
					<s:select name="selectedRole" label="%{getText('modifRole.selectRole')}" list="listRole" size="1" />
					
					<s:submit class="btn btn-default" value="%{getText('bouton.modifier')}">
						<s:param name="id" value="%{#selectedRole}"/>				
					</s:submit>	
					<s:token/>			
				</s:form>
				</s:if>		
			</div>	
		</div>

		
	  	<%@include file="../_include/footer.jsp" %>					
	  	
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>	
		<script>
			function resultatRech(){
				var url = "<s:url action="ajax_getResultatRech?pseudo=%{#idPseudo}"/>";
				console.log(url);

	            // Action AJAX en POST
	            jQuery.post(url, function (data) {
	                    var $formulaireRole = jQuery("#formulaireRole");
	                    $formulaireRole.empty();
	                    jQuery.each(data, function (key, val) {
	                        $formulaireRole.append(
	                            jQuery("<li>")
	                            	.append("<s:checkbox name="checkMe" fieldValue="%{#utilisateur.pseudo}" label="%{#utilisateur.pseudo+' '+#utilisateur.nom+' '+#utilisateur.prenom}"/>")
	                                .append(val.pseudo)
	                                .append(" ")
	                                .append(val.prenom)
	                                .append(" ")
	                                .append(val.nom)
	                                .append("</li>")
	                        );
	                    });
	                })
	                .fail(function () {
	                    alert("Une erreur s'est produite.");
	                });				
			}
		</script>
	</body>
</html>