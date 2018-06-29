<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

	<div class="modal fade" id="myCommModalTopo" tabindex="-1" role="dialog">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title"><s:text name="commTopo.titre"/><s:property value="topo.nom"/></h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	       	<s:actionmessage/>
			<s:actionerror/>
			
			<s:form id="idCommForm" action="commenterTopo" namespace="/jsp/utilisateur" cssClass="form-vertical">
				<s:textfield name="utilisateur.pseudo" placeholder="pseudo" label="pseudo" requiredLabel="true"/>
				<s:textarea name="message" placeholder="message" label="votre message" requiredLabel="true"  cols="50" rows="10"/>	
				<s:submit id="btCommOK" class="btn btn-default" value="%{getText('bouton.envoi')}">
      				<s:param name="pseudo">${utilisateur.pseudo }</s:param>
      				<s:param name="nom" >${topo.nom}</s:param>
      				<s:param name="message">${commentaire.message}</s:param>
      			</s:submit>	
			</s:form>
	      </div>
	    </div>
	  </div>
	</div>
	
	<script type="text/javascript">
	$('#btCommOK').click(function(){
		$('#myCommModalTopo').modal('hide');
	});
	</script>