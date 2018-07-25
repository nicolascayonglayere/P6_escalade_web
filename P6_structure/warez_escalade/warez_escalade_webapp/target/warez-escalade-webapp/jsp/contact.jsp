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
		<title>CONTACT</title>
		<!--<sb:head includeScripts="false" includeStyles="true"/>-->
	</head>

	<body>
		<%@include file="_include/entete.jsp" %>
		<div id="blocPge">
			<div class="container text-center">
				<h1 id="titre"><s:text name="%{getText('contact.titre')}"/></h1>
				</br>
				<s:form id="contactForm" action="envoi_message" cssClass="form-vertical" namespace="/">
					<s:textfield name="coordonneeUtilisateur.email" placeholder="email" label="%{getText('form.email')}" requiredLabel="true"/>
					<s:textfield name="utilisateur.pseudo" placeholder="pseudo" label="%{getText('form.pseudo')}" requiredLabel="true"/>
					<s:textarea name="message" placeholder="message" label="%{getText('form.message')}" requiredLabel="true"  cols="50" rows="10"/>
					<s:submit class="btn btn-default" value="%{getText('bouton.envoi')}"/>		
				<s:token/>
				</s:form>	
			</div>
		</div>

		
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	  	<%@include file="_include/footer.jsp" %>
  	</body>
</html>