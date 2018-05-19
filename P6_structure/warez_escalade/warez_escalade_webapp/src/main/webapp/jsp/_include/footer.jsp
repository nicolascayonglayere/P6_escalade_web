<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<footer>
	<div class="container-fluid">
		<s:if test="#session.utilisateur">
			<p>Utilisateur connecté : <s:property value="#session.utilisateur.pseudo"/></p>
			<p>Topo à retourner : </p>
			<s:a action="logOut" class="btn btn-default btn-sm "><s:text name="DECONNEXION" />
				<s:param name="pseudo" value="userName">#session.utilisateur</s:param>
			</s:a>
			
		</s:if>
		<s:else>
			<p>
				Utilisateur connecté : inconnu
				<s:a href="jsp/_include/login.jsp" class="btn btn-default btn-sm "><s:text name="CONNEXION" /></s:a>
				<%@include file="login.jsp" %>
			</p>
		</s:else>
	
	
		<!--  <div id="bouton_deco" class="col-lg-9">
		  	<div class="navbar-text">
		  		
		  	</div>
		  	
		  	<div> -->	
		  		<!--le bouton pour deco	
	    		<s:url action="loginUser" var="deco">
	    			<s:param name="pseudo" value="pseudo">#session.utilisateur.userName</s:param>
	    		</s:url>
	    		<a class="btn btn-default pull-right" href="${deco }" role="button">DECONNEXION</a>
	  	</div> 		      		
    </div>-->
	
	</div>
    
	<script type="text/javascript">
		$('#myModal').modal('show')
	</script>
	  

</footer>
