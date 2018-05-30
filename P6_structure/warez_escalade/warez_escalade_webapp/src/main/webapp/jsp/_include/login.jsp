<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">AUTHENTIFICATION</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	       	<s:actionmessage/>
			<s:actionerror/>
			
			<s:form id="idLoginForm" action="loginUser" namespace="/LoginUser" cssClass="form-vertical">
				<s:textfield name="utilisateur.pseudo" placeholder="pseudo" label="pseudo" requiredLabel="true"/>
				<s:password name="utilisateur.password" placeholder="password" label="password" requiredLabel="true"/>
      			<input class="btn btn-default" type="submit" value="S'AUTHENTIFIER">
      				<s:param name="pseudo">${utilisateur.pseudo }</s:param>
      				<s:param name="password">${utilisateur.password }</s:param>
     			 </input>		
			</s:form>
	      </div>
	    </div>
	  </div>
	</div>

