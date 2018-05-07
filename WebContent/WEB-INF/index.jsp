<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
		<title>P6_escalade_web</title>
		
		<style type="text/css">
			.navbar-static-top {

			} 
		</style>
	</head>
	
	<body>
		<div class="navbar navbar-inverse navbar-static-top">
			<div class="container">
				<div class="navbar-header">
					<h2>DIAGRAMMES UML P6</h2>
				</div>
				
				<div class="navbar-collapse collapse">
					<!-- la liste des dossiers ds le root -->
					<ul class="nav navbar-nav">
						<c:forEach items="${dossier}" var="nomDossier">
							<li class="dropdown">
								<a href="/P6_escalade_web/servletMenu?${nomDossier}" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
									${nomDossier} 
									<span class="caret"></span>
								</a>
								<!-- la liste des images ds le dossier -->
								<ul class="dropdown-menu">
									<c:forEach items="${image}" var="nomImage">
										<li class="dropdown-item"><a href="/P6_escalade_web/servletDiagramme?${nomImage}"><c:out value="${nomImage}"></c:out></a></li>
									</c:forEach>
								</ul>
							</li>
						</c:forEach>
					</ul>
					
					
				</div>
			</div>
		</div>
		
		
	</body>
</html>