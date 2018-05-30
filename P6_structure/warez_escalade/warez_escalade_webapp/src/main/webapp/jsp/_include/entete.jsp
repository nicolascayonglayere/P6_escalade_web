<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

		<header>




     		 <div class="navbar navbar-expand-md navbar-light bg-light navbar-fixed-top">
      			<div class="container-fluid">
      				<div class="row">
						<!-- <div class="navbar-header">-->
	      					<a class="navbar-brand" href="#page-top">WAREZ GRIMPE</a>	
	      				<!--  </div>-->
	      			
			      		<div class="collapse navbar-collapse"  >
				      		<ul class="navbar-nav mr-auto">
					     	   <li class="hidden"><a href="#page-top"></a></li>
					     	   <li class="nav-item active">
					     	   		<s:url var="listTopoURL" action="lister_topo"/>
					     	   		<s:a href="%{listTopoURL}" cssClass="nav-link">TOPO</s:a>
					     	   </li>
					     	   <li class="nav-item active">
					     	   		<s:url var="presentationURL" action="presentation"/>
					     	   		<s:a href="%{presentationURL}" cssClass="nav-link">QUI SOMMES-NOUS</s:a>
					     	   </li>
					     	   <li class="nav-item active">
					     	   		<s:url var="contactURL" action="contact"/>
					     	   		<s:a href="%{contactURL}" cssClass="nav-link">CONTACT</s:a>
					     	   </li>
					     	   <li class="nav-item active">
					     	   		<s:url var="inscriptionURL" action="inscription"/>
					     	   		<s:a href="%{inscriptionURL}" cssClass="nav-link">S'INSCRIRE</s:a>
					     	   </li>
				      		</ul>
			      		</div>
			      	

      			
      				
      				</div>
		      	</div>
    		 </div>		
		</header>