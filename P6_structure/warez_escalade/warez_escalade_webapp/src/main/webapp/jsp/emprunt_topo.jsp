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
		<title>Emprunt Topo</title>
		<!--<sb:head includeScripts="false" includeStyles="true"/>-->		
	</head>


	<body>
		<%@include file="_include/entete.jsp" %>
		<div id="blocPge">
			<div class = "container text-center">
			
				<h1 id="titre"><s:text name="emprunt.titre"/></h1>
				<s:actionmessage/>
				<table class="table table-bordered table-striped">
					<s:iterator value="listTopo" var="topo">
						<tr>
					    	<td style="text-align:left;">
					    		<s:a action="go_topo" namespace="/">
					            	<s:property value="#topo.nomTopo"/>
				                    <s:param name="nomTopo" value="#topo.nomTopo" />	
				               	</s:a>
					    	</td>
					    	<td style="text-align:right;"><s:property value="#topo.auteur.pseudo" /></td>
					    	<td style="text-align:right;"><s:property value="#topo.nbreEx" /></td>
					    	<td style="text-align:right;">
					    		<s:a action="emprunt_topo" namespace="/jsp/utilisateur">
				    				<s:param name="nom" value="nomTopo"/>
					    			<s:submit class="btn btn-default" value="%{getText('bouton.emprunt')}"/>
					    		</s:a>
					    		
					    	</td>
					   	</tr>
				 	</s:iterator>
				</table>		
			</div>
		</div>
		
		

	  	<script  src="https://code.jquery.com/jquery-3.3.1.min.js"  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="  crossorigin="anonymous"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	  	<%@include file="_include/footer.jsp" %>
	</body>
</html>