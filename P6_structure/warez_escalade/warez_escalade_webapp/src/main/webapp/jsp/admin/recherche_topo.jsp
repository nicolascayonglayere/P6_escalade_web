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
		<title>Rechercher le Topo</title>
		<!--<sb:head includeScripts="false" includeStyles="true"/>-->
	</head>
	
	<body>
		<%@include file="../_include/entete.jsp" %>
		<div id="blocPge">
			<s:actionmessage/>
			<div class="container">
				<h1 id="titre"><s:text name="rechTopo.titre"/></h1>
				<s:if test="%{#request.nomTopo}">
					<s:set var="nomDuTopo"><s:property value="nomTopo"/></s:set>
				</s:if>
				<s:else>
					<s:set var="nomDuTopo" value=" nom du topo "/>
				</s:else>
				<s:form id="rechercheTopo" action="recherche_topo" cssClass="form-vertical" namespace="/jsp/utilisateur">
					<s:textfield name="topo.nomTopo" placeholder="%{nomDuTopo}" label="%{getText('form.nomTopo')}" requiredLabel="true"/>
					<s:submit class="btn btn-default" value="%{getText('bouton.rechercher')}">
				  		<s:param name="nomTopo">${topo.nomTopo}</s:param>
				 	 </s:submit>
				</s:form>
				
				<s:if test="listTopo">
					<s:form action="modif_suppression_topo" namespace="/jsp/utilisateur">	
						<s:iterator value="listTopo" var="topo">				
							<p>
								<s:checkbox name="checkMe" fieldValue="%{#topo.nomTopo}" label="%{#topo.nomTopo+' '+#topo.auteur.pseudo}"/>
							</p>
						</s:iterator>
						<p>
						<s:param name="nomTopo" value="topo.nomTopo"/>
						<s:submit class="btn btn-default" value="%{getText('bouton.selectionner')}">
							<s:param name="nomTopo" value="topo.nomTopo"/>
						</s:submit>					
						</p>
					</s:form>
				</s:if>
			</div>		
		</div>
		<%@include file="../_include/footer.jsp" %>
	  	
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>	
	  			
	</body>
</html>