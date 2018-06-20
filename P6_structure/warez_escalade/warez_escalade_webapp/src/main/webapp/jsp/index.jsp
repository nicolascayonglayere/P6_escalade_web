<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<title>Accueil</title>
		<sb:head includeScripts="true"/>
		<style type="text/css">
	        body {
	            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
	        }
    	</style>		
	</head>


	<body>
		<%@include file="_include/entete.jsp" %>
		<h2><s:text name="index.bienvenue"/></h2>
		<s:actionmessage/>
		<!-- liste des topo ds la bdd -->
		<div class = "container">
			<table class="table table-bordered table-striped">
				<s:iterator value="listTopo">
					<tr>
				    	<td style="text-align:left;">
				    		<s:a action="go_topo" namespace="/">
				            	<s:property value="nom"/>
			                    <s:param name="nom" value="nom" />	
			               	</s:a>
				    	</td>
				    	<td style="text-align:right;"><s:property value="site.nom" /></td>
				    	<!-- <td style="text-align:right;"><s:property value="voie" /></td>-->
				    	<td>

				    		<s:a action="emprunt_topo" namespace="/jsp/utilisateur">
			    				<s:param name="nom" value="nom"/>
				    			<s:submit class="btn btn-default" value="%{getText('bouton.emprunt')}"/>
				    		</s:a>
				    		
				    	</td>
				   	</tr>
			 	</s:iterator>
			</table>		
		</div>

		
		

	  	<script  src="https://code.jquery.com/jquery-3.3.1.min.js"  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="  crossorigin="anonymous"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	  	<%@include file="_include/footer.jsp" %>
	</body>
</html>
