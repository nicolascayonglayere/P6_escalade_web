<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<footer class="footer" >
	<!--  <div class="container" >-->
		<div class="row fixed-bottom align-items-end" id="footer" >
			<s:if test="#session.utilisateur">
				<div class="col-lg-3">
					<s:url var="goCompteURL" action="go_monCompte" namespace="/jsp/utilisateur"/>				
					<s:a href="%{goCompteURL}">
						<s:text name="footer.utilisateur"/><s:property value="#session.utilisateur.pseudo"/>
					</s:a>
				</div>
				<div class="col-lg-2">
					<s:url var="decoURL" action="logOut" namespace="/jsp/utilisateur"/>
					<s:a href="%{decoURL}" class="btn btn-default btn-sm ">
						<s:text name="footer.deconnexion" />
					</s:a>
				</div>
			</s:if>
			<s:else>
				<div class="col-lg-3">
					<s:text name="footer.inconnu"/>
				</div>
				<div class="col-lg-2">					
					<!-- un bouton pour ouvrir la fenetre modale -->
					<button id="btCo" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" data-backdrop="true">
						<s:text name="footer.connexion" />
					</button>
					<%@include file="login.jsp" %>
				</div>
			</s:else>
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-7 mt-2 mt-sm-5">
					<div class="social text-center">
						<s:a class="btn btn-default btn-sm" href="https://www.facebook.com"><i class="fab fa-facebook"></i></s:a>
						<s:a class="btn btn-default btn-sm" href="https://www.twitter.com"><i class="fab fa-twitter-square"></i></s:a>
						<s:a class="btn btn-default btn-sm" href="https://www.instagram.com"><i class="fab fa-instagram"></i></s:a>
						<s:a class="btn btn-default btn-sm" href="https://www.google.com"><i class="fab fa-google-plus"></i></s:a>
					</div>
				</div>
				</hr>	
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mt-2 mt-sm-5 text-center text-white">
					<p><s:text name="footer.moi"/><s:a href="https://www.openclassrooms.com/fr/"><s:text name="footer.oc"/> </s:a></p>
				</div>
				</hr>
		
		</div>
	<!--  </div>-->
    

	  

</footer>

	<script type="text/javascript">
    $('#btCo').click(function(){
    	$('#myModal').modal('show');
    });
	</script>
