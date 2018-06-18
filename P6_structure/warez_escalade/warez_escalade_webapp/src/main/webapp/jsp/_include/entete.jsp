<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

		<header>




     		 <div class="navbar navbar-expand-md navbar-light bg-light navbar-fixed-top">
      			<div class="container-fluid">
      				<div class="row">
						<!-- <div class="navbar-header">-->
							<s:url var="homeURL" action="homeAction" namespace="/"/>
	      					<a class="navbar-brand" href="%{homeURL}"><s:text name="entete.titre"/></a>	
	      				<!--  </div>-->
	      			
			      		<div class="collapse navbar-collapse"  >
				      		<ul class="navbar-nav mr-auto">
					     	   <li class="hidden"><a href="#page-top"></a></li>
					     	   <li class="nav-item active">
					     	   		<s:url var="listTopoURL" action="lister_topo" namespace="/"/>
					     	   		<s:a href="%{listTopoURL}" cssClass="nav-link"><s:text name="entete.topo"/></s:a>
					     	   </li>
					     	   <li class="nav-item active">
					     	   		<s:url var="presentationURL" action="presentation" namespace="/"/>
					     	   		<s:a href="%{presentationURL}" cssClass="nav-link"><s:text name="entete.qui"/></s:a>
					     	   </li>
					     	   <li class="nav-item active">
					     	   		<s:url var="contactURL" action="contact" namespace="/"/>
					     	   		<s:a href="%{contactURL}" cssClass="nav-link"><s:text name="entete.contact"/></s:a>
					     	   </li>
					     	   <li class="nav-item active">
					     	   		<s:url var="inscriptionURL" action="inscription" namespace="/"/>
					     	   		<s:a href="%{inscriptionURL}" cssClass="nav-link"><s:text name="entete.inscription"/></s:a>
					     	   </li>
				      		</ul>
			      		</div>
			      	

      			
      				
      				</div>
		      	</div>
    		 </div>		
		</header>