<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<title>CONTACT</title>
		<sb:head includeScripts="true"/>
		<style type="text/css">
	        body {
	            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
	        }
    	</style>
	</head>

	<body>
		<%@include file="_include/entete.jsp" %>
		<div class="container text-center">
			<s:form id="contactForm" action="envoi_message" theme="bootstrap" cssClass="form-vertical">
				<s:textfield name="email" placeholder="email" label="email" requiredLabel="true"/>
				<s:textfield name="userName" placeholder="UserName" label="UserName" requiredLabel="true"/>
				<s:textarea name="message" placeholder="message" label="votre message" requiredLabel="true"  cols="50" rows="10"/>		
				<input class="btn btn-default" type="submit" value="ENVOYER">
	      			<s:param name="message"><!--le message en param--></s:param>
	     		 </input>
			</s:form>		
		</div>

		<%@include file="_include/footer.jsp" %>
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
  	</body>
</html>