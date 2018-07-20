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
		<link rel="stylesheet" type="text/css" href="../style.css" />
		<title>Supprimer le Topo</title>
		<!--<sb:head includeScripts="false" includeStyles="true"/>-->
	</head>

	<body>
		<%@include file="../_include/entete.jsp" %>
		<div id="blocPge">
			<s:actionmessage/>
			<div class="container">	
				<h1 id="titre"><s:text name="supprTopo.titre"> <s:property value="#request.topo.nomTopo"/></s:text></h1>	
				<div class="col-lg-4">

					<div class="navbar navbar-expand-md navbar-light ">
						<div class="container collapse navbar-collapse">
							<ul class="navbar-nav mr-auto flex-column ">
								<li class="nav-item active">
									<s:a action="ajouter_site" namespace="/jsp/utilisateur" cssClass="nav-link">
										<s:param name="topo.nomTopo" value="topo.nomTopo"/>
										<s:submit class="btn btn-default" value="%{getText('bouton.ajouterSite')}"/>
									</s:a>						
								</li>
								<div class="dropdown-divider"></div>
								<li class="nav-item active">
									<s:form action="ajouter_secteur" namespace="/jsp/utilisateur" cssClass="nav-link">
										<s:if test="listSite">
											<s:select name="selectedSite" label="%{getText('creerTopo.ajouterSecteur')}" list="listSiteSelect" size="1" listKey="id" listvalue="nomSite"/>							
										</s:if>
										<s:submit class="btn btn-default" value="%{getText('bouton.ajouterSecteur')}">
											<s:param name="topo.nomTopo" value="topo.nomTopo"/>
											<s:param name="id" value="%{#selectedSite}"/>								
										</s:submit>
									</s:form>						
								</li>
								<div class="dropdown-divider"></div>
								<li class="nav-item active">					
									<s:form action="ajouter_voie" namespace="/jsp/utilisateur" cssClass="nav-link">
										<s:if test="listSecteur">
											<s:select name="selectedSecteur" label="%{getText('creerTopo.ajouterVoie')}" list="listSecteurSelect" size="1" listKey="id" listvalue="%{listSecteur['secteur.nomSecteur']}"/>
										</s:if>
										<s:submit class="btn btn-default" value="%{getText('bouton.ajouterVoie')}">
											<s:param name="topo.nomTopo" value="topo.nomTopo"/>
											<s:param name="id" value="%{#selectedSecteur}"/>								
										</s:submit>
									</s:form>					
								</li>
								<div class="dropdown-divider"></div>
								<li class="nav-item active">
									<s:a action="ajouter_image" namespace="/jsp/utilisateur" cssClass="nav-link">
										<s:param name="nomTopo" value="topo.nomTopo"/>
										<s:submit class="btn btn-default" value="%{getText('bouton.ajouterImage')}"/>
									</s:a>						
								</li>
							</ul>
						</div>
					</div>
				</div>	
				
				<div class="col_lg_8">
						<ul >
							<s:a action="go_modifTopo" namespace="/jsp/utilisateur">
								<s:text name="modifierTopo.topo"/> <s:property value="#request.topo.nomTopo"/>
			                    <s:param name="nomTopo" value="topo.nomTopo" />
							</s:a>
							<s:a action="supprimerTopo" namespace="/jsp/utilisateur">
							  		<s:param name="nomTopo" value="topo.nomTopo"/>
									<s:submit class="btn btn-default" value="%{getText('bouton.supprimer')}"/>
							</s:a>
							<ul>	
							<s:text name="modifierTopo.titreSite"/>					
							<s:iterator value="listSite" var="site">
								<s:a action="go_modifSite" namespace="/jsp/utilisateur">
										<s:text name="modifierTopo.site"/><s:property value="#site.nomSite"/>
					                    <s:param name="nomSite" value="#site.nomSite" />
					                    <s:param name="nomTopo" value="topo.nomTopo" />							
								</s:a>
								<s:a action="supprimerSite" namespace="/jsp/utilisateur">
					                    <s:param name="nomSite" value="#site.nomSite" />
					                    <s:param name="nomTopo" value="topo.nomTopo" />
										<s:submit class="btn btn-default" value="%{getText('bouton.supprimer')}"/>
								</s:a>														
								<ul>
									<s:text name="modifierTopo.titreSecteur"/>
									<s:iterator value="listSecteur" var="secteur">
										<s:a action="go_modifSecteur" namespace="/jsp/utilisateur">
												<s:text name="modifierTopo.secteur"/><s:property value="#secteur.nomSecteur"/>
							                    <s:param name="nomSecteur" value="#secteur.nomSecteur" />
							                    <s:param name="nomSite" value="site.nomSite"/>
							                    <s:param name="nomTopo" value="topo.nomTopo" />							
										</s:a>
										<s:a action="supprimerSecteur" namespace="/jsp/utilisateur">
												<s:param name="nomSecteur" value="#secteur.nomSecteur" />
							                    <s:param name="nomSite" value="site.nomSite" />
							                    <s:param name="nomTopo" value="topo.nomTopo" />
												<s:submit class="btn btn-default" value="%{getText('bouton.supprimer')}"/>
										</s:a>									
										<ul>
											<s:text name="modifierTopo.titreVoie"/>
											<s:iterator value="#secteur.listVoie" var="voie">
												<ul>
												<s:a action="go_modifVoie" namespace="/jsp/utilisateur">
														<s:text name="modifierTopo.voie"/> <s:property value="#voie.nomVoie"/>
														<s:param name="nomVoie" value="#voie.nomVoie"/>
									                    <s:param name="nomSecteur" value="secteur.nomSecteur" />
									                    <s:param name="nomSite" value="site.nomSite"/>
									                    <s:param name="nomTopo" value="topo.nomTopo" />							
												</s:a>
												<s:a action="supprimerVoie" namespace="/jsp/utilisateur">
														<s:param name="nomVoie" value="#voie.nomVoie"/>
														<s:param name="nomSecteur" value="secteur.nomSecteur" />
									                    <s:param name="nomSite" value="site.nomSite" />
									                    <s:param name="nomTopo" value="topo.nomTopo" />
														<s:submit class="btn btn-default" value="%{getText('bouton.supprimer')}"/>
												</s:a>											
												</ul>										
											</s:iterator>
										</ul>
									</s:iterator>
								</ul>
							</s:iterator>
							</ul>
						</ul>	
				</div>
				</div>			
			
		</div>	
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>	
	  	<%@include file="../_include/footer.jsp" %>			
	</body>
</html>