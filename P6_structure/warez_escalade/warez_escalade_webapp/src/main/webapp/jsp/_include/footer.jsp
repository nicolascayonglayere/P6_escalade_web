<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<footer class="footer" >
	<div class="container center">
		<div class="row">
			<s:if test="#session.utilisateur">
				<p>
					<s:url var="goCompteURL" action="go_monCompte"/>				
					<s:a href="%{goCompteURL}">
						<s:text name="footer.utilisateur"/><s:property value="#session.utilisateur.pseudo"/>
					</s:a>
				</p>
				
				<s:url var="decoURL" action="logOut"/>
				<s:a href="%{decoURL}" class="btn btn-default btn-sm ">
					<s:text name="footer.deconnexion" />
				</s:a>
				
			</s:if>
			<s:else>
				<p>
					<s:text name="footer.inconnu"/>
					<!-- un bouton pour ouvrir la fenetre modale -->
					<button id="btCo" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" data-backdrop="true">
						<s:text name="footer.connexion" />
					</button>
					<%@include file="login.jsp" %>
				</p>
			</s:else>
		</div>
	</div>
    

	  

</footer>

	<script type="text/javascript">
    $('#btCo').click(function(){
    	$('#myModal').modal('show');
    });
	</script>
