<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<footer class="footer container" >
	<div class="container" >
		<div class="fixed-bottom align-items-center" id="footer" >
			<div class="row">
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
					<s:submit class="btn btn-default" value="%{getText('bouton.mesEmprunts')}" onclick="bilanEmprunt()" id="bouton"/>
					<div id="blocAjax">
					
					</div>
				</s:if>
				<s:else>
					<div class="col-lg-3">
						<s:text name="footer.inconnu"/>
					</div>
					<div class="col-lg-2">					
						<!-- un bouton pour ouvrir l'authentification -->
						<s:a action="go_login" namespace="/">
							<s:submit class="btn btn-default" value="%{getText('footer.connexion')}"/>
						</s:a>
						
					</div>
				</s:else>
				<div class="col-xs-12 col-sm-12 col-md-7 col-lg-7 mt-2 mt-sm-5">
					<div class="social text-center">
						<s:a class="btn btn-default btn-sm" href="https://www.facebook.com"><i class="fab fa-facebook"></i></s:a>
						<s:a class="btn btn-default btn-sm" href="https://www.twitter.com"><i class="fab fa-twitter-square"></i></s:a>
						<s:a class="btn btn-default btn-sm" href="https://www.instagram.com"><i class="fab fa-instagram"></i></s:a>
						<s:a class="btn btn-default btn-sm" href="https://www.google.com"><i class="fab fa-google-plus"></i></s:a>
					</div>
				</div>
			</div>	
				</hr>	
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mt-2 mt-sm-5 text-center text-white">
					<p><s:text name="footer.moi"/><s:a href="https://www.openclassrooms.com/fr/"><s:text name="footer.oc"/> </s:a></p>
				</div>
				</hr>
		
		</div>
	 </div>
    

	  

</footer>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script>
			//$("bouton").click(
				function bilanEmprunt(){
					//e.preventDefault();
					var url = "<s:url action="jsp/ajax_listEmprunt"/>";
					
					console.log(url);
	
		            // Action AJAX en POST
		            jQuery.post(url, function (data) {
		                    var $blocAjax = jQuery("#blocAjax");
		                    $blocAjax.empty();
		                    console.log(data);
		                    jQuery.each(data, function (key, val) {
		                    	console.log(val);
		                        $blocAjax.append(
		                            jQuery("<li>")
		                                .append(val.topo.nomTopo)
		                                .append(" ")
		                                .append(val.dateEmprunt)
		                                .append(" ")
		                                .append(val.dateRetour)
		                                .append("</li>")
		                        );
		                    });
		                })
		                .fail(function () {
		                    alert("Une erreur s'est produite.");
		                });				
				}
			//);
		</script>