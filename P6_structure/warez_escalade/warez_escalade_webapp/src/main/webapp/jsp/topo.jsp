<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">		
		<title>TOPO</title>
	</head>
	<body>
		<%@include file="_include/entete.jsp" %>
		<h2><s:property value="topo.nom"/></h2>
		<h3><s:property value="site.nom"/></h3>
		
		<!-- google map -->
		
		<!-- tableau voie/secteur -->
		<table>
			<s:iterator value="listSecteur">
				<tr>
					<td style="text-align:center;"><s:property value="secteur.nom"/></td>
				</tr>
				<tr>
					<td>
						<table class="table table-bordered table-striped">
								<s:iterator value="listVoie">
									<tr>
								    	<td style="text-align:right;"><s:property value="voie.nom"/></td>
								    	<td style="text-align:right;"><s:property value="voie.cotation" /></td>
								    	<td style="text-align:right;"><s:property value="voie.nbreLongueur" /></td>
								    	<td style="text-align:right;"><s:property value="voie.nbrePoint" /></td>
								   	</tr>
							 	</s:iterator>
						</table>					
					</td>
				</tr>
		

			</s:iterator>
		</table>
		
		
		
		
		
		
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>		
	</body>
</html>