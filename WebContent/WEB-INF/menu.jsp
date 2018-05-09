<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	
	
		<div class="navbar navbar-expand-lg navbar-light bg-light navbar-static-top">
			<div class="container">
				<div class="navbar-brand">
					DIAGRAMMES UML P6
				</div>
				
				<div class="navbar-collapse collapse">
					<!-- la liste des dossiers ds le root -->
					<ul class="navbar-nav">
						<c:forEach items="${dossier}" var="nomDossier">
							<li class="nav-item dropdown">
								<a href="/P6_escalade_web/servletMenu?${nomDossier}" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									${nomDossier} 
								</a>
								<!-- la liste des images ds le dossier -->
								<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
									<c:forEach items="${image}" var="nomImage">
										<a class="dropdown-item" href="/P6_escalade_web/servletDiagramme?image=${nomImage}&dossier=${nomDossier}"><c:out value="${nomImage}"></c:out></a>
									</c:forEach>
								</ul>
							</li>
						</c:forEach>
					</ul>
					
					
				</div>
			</div>
		</div>
		

		
