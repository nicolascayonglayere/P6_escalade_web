<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<title>Rechercher le Topo</title>
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
			<s:form id="rechercheTopo" action="recherche_topo" cssClass="form-horizontal" namespace="/jsp/utilisateur">
				<s:textfield name="topo.nomTopo" placeholder="nom du topo" label="%{getText('form.nomTopo')}" requiredLabel="true"/>
				<s:submit class="btn btn-default" value="%{getText('bouton.rechercher')}">
			  		<s:param name="nomTopo">${topo.nomTopo}</s:param>
			 	 </s:submit>
			</s:form>

				<s:iterator value="listTopo">
					<ul>
						<s:a action="go_modifTopo" namespace="/jsp/utilisateur">
							<s:text name="modifierTopo.topo"/> <s:property value="nomTopo"/>
		                    <s:param name="nomTopo" value="nomTopo" />
						</s:a>
						<s:a action="supprimerTopo" namespace="/jsp/utilisateur">
							<s:submit class="btn btn-default" value="%{getText('bouton.supprimer')}">
						  		<s:param name="nomTopo">${topo.nomTopo}</s:param>
						 	 </s:submit>							
						</s:a>
						<ul>	
						<s:text name="modifierTopo.titreSite"/>					
						<s:iterator value="listSite" var="site">
							<s:a action="go_modifSite" namespace="/jsp/utilisateur">
									<s:text name="modifierTopo.site"/><s:property value="#site.nomSite"/>
				                    <s:param name="nomSite" value="site.nomSite" />
				                    <s:param name="nomTopo" value="topo.nomTopo" />							
							</s:a>
							<s:a action="supprimerSite" namespace="/jsp/utilisateur">
								<s:submit class="btn btn-default" value="%{getText('bouton.supprimer')}">
				                    <s:param name="nomSite" value="site.nomSite" />
				                    <s:param name="nomTopo" value="topo.nomTopo" />
							 	 </s:submit>							
							</s:a>														
							<ul>
								<s:text name="modifierTopo.titreSecteur"/>
								<s:iterator value="listSecteur" var="secteur">
									<s:a action="go_modifSecteur" namespace="/jsp/utilisateur">
											<s:text name="modifierTopo.secteur"/><s:property value="#secteur.nomSecteur"/>
						                    <s:param name="nomSecteur" value="secteur.nomSecteur" />
						                    <s:param name="nomSite" value="site.nomSite"/>
						                    <s:param name="nomTopo" value="topo.nomTopo" />							
									</s:a>
									<s:a action="supprimerSecteur" namespace="/jsp/utilisateur">
										<s:submit class="btn btn-default" value="%{getText('bouton.supprimer')}">
											<s:param name="nomSecteur" value="%{#secteur.getNomSecteur}" />
						                    <s:param name="nomSite" value="site.nomSite" />
						                    <s:param name="nomTopo" value="topo.nomTopo" />
									 	 </s:submit>							
									</s:a>									
									<ul>
										<s:text name="modifierTopo.titreVoie"/>
										<s:iterator value="listVoie" var="voie">
											<ul>
											<s:a action="go_modifVoie" namespace="/jsp/utilisateur">
													<s:text name="modifierTopo.voie"/> <s:property value="#voie.nomVoie"/>
													<s:param name="nom" value="voie.nomVoie"/>
								                    <s:param name="nomSecteur" value="secteur.nomSecteur" />
								                    <s:param name="nomSite" value="site.nomSite"/>
								                    <s:param name="nomTopo" value="topo.nomTopo" />							
											</s:a>
											<s:a action="supprimerVoie" namespace="/jsp/utilisateur">
												<s:submit class="btn btn-default" value="%{getText('bouton.supprimer')}">
													<s:param name="nom" value="voie.nomVoie"/>
													<s:param name="nomSecteur" value="secteur.nomSecteur" />
								                    <s:param name="nomSite" value="site.nomSite" />
								                    <s:param name="nomTopo" value="topo.nomTopo" />
											 	 </s:submit>							
											</s:a>											
											</ul>										
										</s:iterator>
									</ul>
								</s:iterator>
							</ul>
						</s:iterator>
						</ul>
					</ul>
		
				</s:iterator>
		</div>
		
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>	
	  	<%@include file="../_include/footer.jsp" %>		
	</body>
</html>