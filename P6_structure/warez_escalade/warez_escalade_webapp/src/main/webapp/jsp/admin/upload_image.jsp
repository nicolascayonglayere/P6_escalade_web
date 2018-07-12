<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="../style.css" />
		<title>Creer Topo : upload image</title>
		<sb:head/>
	</head>
	
	<body>
		<%@include file="../_include/entete.jsp" %>
		<s:actionmessage/>
		<div id="blocPge">
			<div class="container text-center">
				<h2 id="titre"><s:text name="image.titre"/></h2>
				<h3 id="titre"><s:text name="creerTopo.topo"/> <s:property value="#session.topo.nomTopo"/></h3>		
					<s:form action="upload_image" method="POST" enctype="multipart/form-data" cssClass="form-horizontal">
	  					<s:file label="%{getText('form.imgCouv')}" name="upload" />
	  					<s:file label="%{getText('form.img')}" name="upload" />
	  					<s:file label="%{getText('form.img')}" name="upload" />
	  					<s:file label="%{getText('form.img')}" name="upload" />
	  					<s:submit cssClass="btn btn-primary" value="%{getText('bouton.valider')}"/>
					</s:form>		
			</div>
		</div>
		
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>	
	  	<%@include file="../_include/footer.jsp" %>		
	</body>
</html>