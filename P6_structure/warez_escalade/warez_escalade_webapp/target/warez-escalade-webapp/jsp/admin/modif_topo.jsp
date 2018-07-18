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
		<title>Modifier Topo</title>
		<!--<sb:head includeScripts="false" includeStyles="true"/>-->
	</head>
	
	<body>
		<%@include file="../_include/entete.jsp" %>
		<div id="blocPge">
			<s:actionmessage/>
			<div class="container text-center">	
				<h1 id="titre"><s:text name="modifierTopo.titre"/> <s:property value="topo.nomTopo"/></h1>
				<s:form action="modifier_topo" cssClass="form-vertical" namespace="/jsp/utilisateur">
					<s:textfield name="topo.nomTopo" placeholder="topo.nomTopo" label="%{getText('form.nomTopo')}" requiredLabel="true"/>
					<s:textfield name="topo.nbreEx" placeholder="topo.nbreEx" label="%{getText('form.exemplaire')}" requiredLabel="true"/>
					<s:textfield name="topo.description" placeholder="topo.description" label="%{getText('form.description')}" requiredLabel="false"/>
					<s:textfield name="topo.longitude" placeholder="topo.longitude" label="%{getText('form.longitude')}" requiredLabel="true"/>
					<s:textfield name="topo.latitude" placeholder="topo.latitude" label="%{getText('form.latitude')}" requiredLabel="true"/>
					<s:submit class="btn btn-default" value="%{getText('bouton.valider')}">
	      				<s:param name="nomTopo">${topo.nomTopo}</s:param>
	      				<s:param name="nbreEx">${topo.nbreEx}</s:param>
	      				<s:param name="description">${topo.description}</s:param>
	      				<s:param name="longitude">${topo.longitude}</s:param>
	      				<s:param name="latitude">${topo.latitude}</s:param>
	     			 </s:submit>
	     			 <s:token/>
				</s:form>
			</div>				
		</div>
		
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>	
	  	<%@include file="../_include/footer.jsp" %>
	</body>
</html>