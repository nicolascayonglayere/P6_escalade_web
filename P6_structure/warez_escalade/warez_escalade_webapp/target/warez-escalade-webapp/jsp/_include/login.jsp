<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

	<div class="modal" id="myModal" tabindex="-1" role="dialog">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h1 class="modal-title"><s:text name="login.titre"/></h1>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	       	<s:actionmessage/>
			<s:actionerror/>
			
			<s:form id="idLoginForm" action="loginUser" cssClass="form-vertical" namespace="/">
				<s:textfield name="utilisateur.pseudo" placeholder="pseudo" label="%{getText('form.pseudo')}" requiredLabel="true"/>
				<s:password name="utilisateur.password" placeholder="password" label="%{getText('form.mdp')}" requiredLabel="true"/>
				<s:submit id="btOK" class="btn btn-default" value="%{getText('login.titre')}">	
       				<s:param name="pseudo">${utilisateur.pseudo }</s:param>
      				<s:param name="password">${utilisateur.password }</s:param>
     			 </s:submit>
     			 <s:token/>		
			</s:form>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<script type="text/javascript">
    $('#btCo').click(function(){
    	$('#myModal').modal('show');
    });
    
	$('#btOK').click(function(){
		$('#myModal').modal('hide');
	});
	</script>
