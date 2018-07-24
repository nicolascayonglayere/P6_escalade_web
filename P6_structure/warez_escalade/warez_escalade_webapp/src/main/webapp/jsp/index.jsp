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
		<title>Accueil</title>
		<!--<sb:head includeScripts="false" includeStyles="true"/>-->	
	</head>


	<body>
		<%@include file="_include/entete.jsp" %>
		<div id="blocPge">
			<div class = "container">

				<h1 id="titre"><s:text name="index.bienvenue"/></h1>
				<s:actionmessage/>
				<s:if test="#session.utilisateur">
					<p><s:text name="index.bonjour"/> <s:property value="#session.utilisateur.pseudo"/></p>	
				</s:if>
				<s:else>
					<p><s:text name="index.connexion"/></p>
				</s:else>	
					
				<div class="row">
					<s:iterator value="listTopo" var="topo">
				    	<div style="text-align:center;" class="col-lg-3">
				    		<s:a action="go_topo" namespace="/">
				    			<!-- mettre l'image de couv -->
								<s:url action="ImageAction" namespace="/" var="URLTag">
									<s:param name="imageId" value="%{#topo.image}"/>
								</s:url>
								<img src="<s:property value="#URLTag"/>"/>			    			
				            	<s:property value="#topo.nomTopo"/></br>
				            	<s:property value="%{#topo.auteur.pseudo}"/>
			                    <s:param name="nomTopo" value="nomTopo" />	
			               	</s:a>
				    	</div>
				 	</s:iterator>
				</div>
	  					
			</div>	
				
		

		
		



		
		</div>
		<%@include file="_include/footer.jsp" %>
		<script  src="https://code.jquery.com/jquery-3.3.1.min.js"  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="  crossorigin="anonymous"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	</body>
</html>
