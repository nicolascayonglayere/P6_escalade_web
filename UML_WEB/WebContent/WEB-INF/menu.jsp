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

							<li class="nav-item dropdown">
								<a href="/P6_escalade_web/servlet?diag_d_activite" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									diag_d_activite 
								</a>
								<!-- la liste des images ds le dossier -->
								<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
									<c:forEach items="${diagActivite}" var="nomImage">
										<a class="dropdown-item" href="/P6_escalade_web/servletDiagramme?image=${nomImage}&dossier=diag_d_activite"><c:out value="${nomImage}"></c:out></a>
									</c:forEach>
								</ul>
							</li>
							
							<li class="nav-item dropdown">
								<a href="/P6_escalade_web/servlet?de_cas_d'utilisation" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									diag_de_cas_d_utilisation
								</a>
								<!-- la liste des images ds le dossier -->
								<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
									<c:forEach items="${diagCasUtili}" var="nomImage">
										<a class="dropdown-item" href="/P6_escalade_web/servletDiagramme?image=${nomImage}&dossier=diag_de_cas_d_utilisation"><c:out value="${nomImage}"></c:out></a>
									</c:forEach>
								</ul>
							</li>

							<li class="nav-item dropdown">
								<a href="/P6_escalade_web/servlet?diag_de_classe" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									diag_de_classe
								</a>
								<!-- la liste des images ds le dossier -->
								<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
									<c:forEach items="${diagClasse}" var="nomImage">
										<a class="dropdown-item" href="/P6_escalade_web/servletDiagramme?image=${nomImage}&dossier=diag_de_classe"><c:out value="${nomImage}"></c:out></a>
									</c:forEach>
								</ul>
							</li>
							
							<li class="nav-item dropdown">
								<a href="/P6_escalade_web/servlet?diag_de_sequence" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									diag_de_sequence
								</a>
								<!-- la liste des images ds le dossier -->
								<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
									<c:forEach items="${diagSeq}" var="nomImage">
										<a class="dropdown-item" href="/P6_escalade_web/servletDiagramme?image=${nomImage}&dossier=diag_de_sequence"><c:out value="${nomImage}"></c:out></a>
									</c:forEach>
								</ul>
							</li>
							
							<li class="nav-item dropdown">
								<a href="/P6_escalade_web/servlet?diag_package" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									diag_package
								</a>
								<!-- la liste des images ds le dossier -->
								<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
									<c:forEach items="${diagPack}" var="nomImage">
										<a class="dropdown-item" href="/P6_escalade_web/servletDiagramme?image=${nomImage}&dossier=diag_package"><c:out value="${nomImage}"></c:out></a>
									</c:forEach>
								</ul>
							</li>													
					</ul>
					
					
				</div>
			</div>
		</div>
		

		
