<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<title>TOPO</title>
		<style>
      	/* Always set the map height explicitly to define the size of the div
       	* element that contains the map. */
      	#map {
        	height: 100%;
    	 }
    	html, body {
        	height: 100%;
        	margin: 0;
        	padding: 0;
        	padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
      	}
	    </style>
		<sb:head includeScripts="true"/>
	</head>
	<body>
		<%@include file="_include/entete.jsp" %>
		<div class="container">
			<div class="row">
				<div class="container">
					<h2><s:property value="topo.nom"/></h2>
					<h3><s:property value="site.nom"/></h3>			
				</div>

				
				<!-- google map -->
				<div id="map"></div>			
			</div>
			

			
			<!-- tableau voie/secteur -->
			<div class="row">
				<div class="container">
					<table>
						<s:iterator value="listSecteur" var="secteur">
							<tr>
								<td style="text-align:center;">Secteur : <s:property value="#secteur.nom"/></td>
							</tr>
							<tr>
								<td>
									<table class="table table-bordered table-striped">
										<thead>
											<tr>
												<th>Nom</th>
												<th>Cotation</th>
												<th>Hauteur</th>
												<th>Nombre de longueurs</th>
												<th>Nombre de points</th>
											</tr>
										</thead>
											<s:iterator value="listVoie" var="voie">
												<tr>
											    	<td style="text-align:left;"><s:property value="#voie.nom"/></td>
											    	<td style="text-align:right;"><s:property value="#voie.cotation" /></td>
											    	<td style="text-align:right;"><s:property value="#voie.hauteur" /></td>
											    	<td style="text-align:right;"><s:property value="#voie.nbLgueur" /></td>
											    	<td style="text-align:right;"><s:property value="#voie.nbPoint" /></td>
											   	</tr>
										 	</s:iterator>
									</table>					
								</td>
							</tr>
						</s:iterator>
					</table>				
				</div>
			</div>
		</div>
		
		<%@include file="_include/footer.jsp" %>
		
		<!-- les scripts google Map API -->
		<script>
	      function initMap() {
	        var chicago = new google.maps.LatLng(41.850, -87.650);
	
	        var map = new google.maps.Map(document.getElementById('map'), {
	          center: chicago,
	          zoom: 10
	        });
	
	        var coordInfoWindow = new google.maps.InfoWindow();
	        coordInfoWindow.setContent(createInfoWindowContent(chicago, map.getZoom()));
	        coordInfoWindow.setPosition(chicago);
	        coordInfoWindow.open(map);
	
	        map.addListener('zoom_changed', function() {
	          coordInfoWindow.setContent(createInfoWindowContent(chicago, map.getZoom()));
	          coordInfoWindow.open(map);
	        });
	      }
	
	      var TILE_SIZE = 256;
	
	      function createInfoWindowContent(latLng, zoom) {
	        var scale = 1 << zoom;
	
	        var worldCoordinate = project(latLng);
	
	        var pixelCoordinate = new google.maps.Point(
	            Math.floor(worldCoordinate.x * scale),
	            Math.floor(worldCoordinate.y * scale));
	
	        var tileCoordinate = new google.maps.Point(
	            Math.floor(worldCoordinate.x * scale / TILE_SIZE),
	            Math.floor(worldCoordinate.y * scale / TILE_SIZE));
	
	        return [
	          'Chicago, IL',
	          'LatLng: ' + latLng,
	          'Zoom level: ' + zoom,
	          'World Coordinate: ' + worldCoordinate,
	          'Pixel Coordinate: ' + pixelCoordinate,
	          'Tile Coordinate: ' + tileCoordinate
	        ].join('<br>');
	      }
	
	      // The mapping between latitude, longitude and pixels is defined by the web
	      // mercator projection.
	      function project(latLng) {
	        var siny = Math.sin(latLng.lat() * Math.PI / 180);
	
	        // Truncating to 0.9999 effectively limits latitude to 89.189. This is
	        // about a third of a tile past the edge of the world tile.
	        siny = Math.min(Math.max(siny, -0.9999), 0.9999);
	
	        return new google.maps.Point(
	            TILE_SIZE * (0.5 + latLng.lng() / 360),
	            TILE_SIZE * (0.5 - Math.log((1 + siny) / (1 - siny)) / (4 * Math.PI)));
	      }
	    </script>
	    
	    <script async defer src="https://maps.googleapis.com/maps/api/js?key= AIzaSyBW9tdPhwEC0knxkB11uwv1p8ehQpB3BaY &callback=initMap"></script>
		
		
		
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	</body>
</html>