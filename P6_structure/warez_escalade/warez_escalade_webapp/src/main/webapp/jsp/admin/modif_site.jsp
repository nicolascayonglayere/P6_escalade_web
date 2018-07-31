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
		<title>Modification du Site</title>
		<!--<sb:head includeScripts="false" includeStyles="true"/>-->
	</head>

	<body>
		<%@include file="../_include/entete.jsp" %>
		<div id="blocPge">
			<s:actionmessage/>
			<div class="container text-center">
				<h1 id="titre"><s:text name="modifierTopo.topo"/> <s:property value="topo.nomTopo"/></h1>
				<h4 id="titre"><s:text name="modifierTopo.titreSite"/> <s:property value="site.nomSite"/></h4>
					<s:form action="modifier_site" cssClass="form-vertical" namespace="/jsp/utilisateur">
						<s:textfield name="site.nomSite" placeholder="nom du site" label="%{getText('form.nomSite')}" requiredLabel="false"/>
						<s:textfield name="site.description" placeholder="description" label="%{getText('form.description')}" requiredLabel="false"/>
						<s:textfield name="topo.nomTopo" placeholder="topo.nomTopo" label="%{getText('form.nomTopo')}" requiredLabel="true"/>
						<s:submit class="btn btn-default" value="%{getText('bouton.valider')}">
		      				<s:param name="nomSite">${site.nomSite}</s:param>
		      				<s:param name="description">${site.description}</s:param>
		      				<s:param name="nomTopo">${topo.nomTopo}</s:param>
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