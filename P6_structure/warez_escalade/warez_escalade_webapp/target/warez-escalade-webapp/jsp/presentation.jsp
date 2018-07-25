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
		<title>Présentation</title>
		<!--<sb:head includeScripts="false" includeStyles="true"/>-->
	</head>

	<body>
		<%@include file="_include/entete.jsp" %>
		<div id="blocPge">
		<div class="container">
			<h1 id="titre"><s:text name="presentation.titre"/></h1>
			<section>
				<h3><s:text name="presentation.sousTitre"/></h3>
				<p><s:text name="presentation.description"/></br></p>
			</section>
			<section>	
				<h3><s:text name="presentation.equipe"/></h3>
				<p><s:text name="presentation.administrateur"/></p>
				<ul>
					<s:iterator value="listAdmin" var="utilisateur">
						<li><s:property value="#utilisateur.pseudo"/></li>
					</s:iterator>
				</ul>
				<p><s:text name="presentation.administrateurDescr"/></br></p>
				
				<p><s:text name="presentation.moderateur"/></p>
				<ul>
					<s:iterator value="listModo" var="utilisateur">
						<li><s:property value="#utilisateur.pseudo"/></li>
					</s:iterator>
				</ul>
				<p><s:text name="presentation.moderateurDescr"/></p>
				
			</section>		
		</div>
		</div>
		
		
		
		
		
	  	<%@include file="_include/footer.jsp" %>		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

	</body>
</html>