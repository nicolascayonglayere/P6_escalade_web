<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<footer class="footer" >
	<div class="container center">
		<div class="row">
			<s:if test="#session.utilisateur">
				<p> Utilisateur connecté : <s:property value="#session.utilisateur.pseudo"/></p>
				<p> Topo à retourner : </p>
				<s:a action="logOut" class="btn btn-default btn-sm "><s:text name="DECONNEXION" />
					<s:param name="pseudo" value="userName"> #session.utilisateur</s:param>
				</s:a>
				
			</s:if>
			<s:else>
				<p>
					Utilisateur connecté : inconnu
					<!--<s:a href="jsp/_include/login.jsp" class="btn btn-default btn-sm "><s:text name="CONNEXION" /></s:a>-->
					<!-- un bouton pour ouvrir la fenetre modale -->
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" data-backdrop="true">CONNEXION</button>
					<%@include file="login.jsp" %>
				</p>
			</s:else>
		</div>
	</div>
    

	  

</footer>

	<script type="text/javascript">
		$('#myModal').modal('show')
	</script>
