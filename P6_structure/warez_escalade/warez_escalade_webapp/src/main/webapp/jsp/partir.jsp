<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="/warez-escalade-webapp/jsp/style.css" />
		<title>TOPO</title>
		<!--<sb:head includeScripts="false" includeStyles="true"/>-->
	</head>
	<body>
		<%@include file="_include/entete.jsp" %>
	
		<div id="blocPge">
			<div class="container">			
				<s:actionmessage/>
				<h1 id="titre"><s:text name="partir.titre"/></h1>
				<h3><s:text name="partir.explication"></s:text></h3>
				<!--<s:form action="rechMulti" cssClass="form-vertical" namespace="/">-->
				<s:form>
					<div class="row">
						<s:checkbox name="checkMeTopo" label="%{getText('compteUser.topo')}"/>
						<s:checkbox name="checkMeSite" label="%{getText('creerTopo.site')}"/>
						<s:checkbox name="checkMeSecteur" label="%{getText('creerTopo.secteur')}"/>
						<s:checkbox name="checkMeVoie" label="%{getText('modifierTopo.voie')}"/>
					</div>
					<s:textfield name="nom" placeholder="nom" label="%{getText('form.nomTopo')}" requiredLabel="true"/>
					<s:text name="partir.difficulte"/>
					<s:select name="selectedMin" label="%{getText('partir.minDiff')}" list="listDiff" size="1" />
					<s:select name="selectedMax" label="%{getText('partir.maxDiff')}" list="listDiff" size="1" />
					<s:submit class="btn btn-default" value="%{getText('bouton.valider')}" onclick="resultatRecherche()">
						<s:param name="nom" value="nom"/>
					</s:submit>					
				</s:form>
				<!-- </s:form> -->
				
				<s:if test="listResultat">
					<s:form action="emprunt_topo" namespace="/jsp/utilisateur">	
						<s:iterator value="listTopo" var="topo">				
							<p id="listTopo">
								<s:checkbox name="checkMe" fieldValue="%{#topo.nomTopo}" label="%{#topo.nomTopo+' '+#topo.auteur.pseudo}"/>
									<s:iterator value="#topo.listVoie" var="voie">
										<li><s:property value="#voie.nomVoie"/> <s:property value="#voie.cotation"/> <s:property value="#voie.description"/></li>											
									</s:iterator>
							</p>
						</s:iterator>
						<p>
						<s:param name="nomTopo" value="topo.nomTopo"/>
						<s:if test="#session.utilisateur">
							<s:submit class="btn btn-default" value="%{getText('bouton.emprunt')}">
								<s:param name="nomTopo" value="topo.nomTopo"/>
							</s:submit>					
						</s:if>
						<s:else>
							<s:text name="emprunt.titre"></s:text>
						</s:else>
						</p>
					</s:form>
				</s:if>
				
			</div>
		</div>
	  	<%@include file="_include/footer.jsp" %>		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
   		<script>
	        function resultatRecherche() {
            // URL de l'action AJAX
            var url = "<s:url action="ajax_getResultatRech" namespace="/" />";
            // Action AJAX en POST
            jQuery.post(url, function (data) {
                    var $listTopo = jQuery("#listTopo");
                    $listTopo.empty();
                    jQuery.each(data, function (key, val) {
                    	$('listTopo').append(
                                jQuery("<li>")
                                .append(val.topo.nom)
                                .append(val.topo.auteur.pseudo)
                                .append("</li>")
                    	);
                    });
                })
                .fail(function () {
                    alert("Une erreur s'est produite.");
                });
	        }
    	</script>	  			
	</body>
</html>