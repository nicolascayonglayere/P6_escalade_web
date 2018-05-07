<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
		<title>Diagramme</title>
		<style type="text/css">
			.container {
 			 padding-left: 160px;
 			 margin-left: 160px;
			}
			#monImg{
			margin: 5,5,5,5px;
			} 
		</style>
	</head>

	<body>
		<div class="container">
			<div class="row">
				<div >
					<%@include file="index.jsp" %>

				</div>
		
				<div id="monImg">
					<h2>${nomIllus }</h2>
					<!-- l'image a afficher par defaut package diag -->
					<li><img alt="${nomIllus}" src="${cheminIllus }"></li>
				</div>
						
			</div>
	
		</div>

		
	</body>
</html>