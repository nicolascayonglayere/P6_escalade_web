<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

		<header>

     		 <div class="navbar navbar-inverse navbar-fixed-top">
      			<div class="container-fluid">
      				<div class="row">
						<div class="navbar-header">
	      					<a class="navbar-brand" href="#page-top">WAREZ GRIMPE</a>	
	      				</div>
	      			
			      		<div  >
				      		<ul class="nav navbar-nav ">
					     	   <li class="hidden"><a href="#page-top"></a></li>
					     	   <li class="active"><a href="<s:url action="lister_chan"/>">Selection du channel</a></li>
				      		</ul>
			      		</div>
			      	
			      		<div id="bouton_deco" class="col-lg-9">
					      	<div class="navbar-text">
					      		<p>Utilisateur connecté : <s:property value="#session.utilisateur.userName"/></p>
					      	</div>
					      	
					      	<div>
					      		<!--le bouton pour deco-->	
			      				<s:url action="loginUser" var="deco">
			      					<s:param name="pseudo" value="pseudo">#session.utilisateur.userName</s:param>
			      				</s:url>
			      				<a class="btn btn-default pull-right" href="${deco }" role="button">DECONNEXION</a>
					      	</div>			      		
			      		</div>
      			
      				
      				</div>
		      	</div>
    		 </div>		
		</header>