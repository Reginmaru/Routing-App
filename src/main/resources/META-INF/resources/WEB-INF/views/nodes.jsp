<%@ page contentType="text/html;charset=ISO-8859-1" language="java" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta charset="ISO-8859-1">
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="src.main.java.com.example.RoutingApp.node.nodeService.*"%>
<% 
System.out.println(request.getAttribute("list"));
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
window.onload = function() { 
 
var chart = new CanvasJS.Chart("chartContainer", {  
	animationEnabled: true,
	theme: "light2",
	title: {
		text: "Relationship b/w Weight and Fuel Economy of Cars"
	},
	subtitles: [{
		text: "Fuel Economy in Miles per Gallon(MPG)"
	}],
	axisX:{
	lineThickness: 0,
	tickThickness: 0,
	valueFormatString:" "
},
axisY:{
	lineThickness: 0,
	gridThickness: 0,
	tickLength: 0,
	valueFormatString:" "
},
	data: [{
		type: "scatter",
		xValueFormatString: "#,##0.00 Thousand Pounds",
		yValueFormatString: "#,##0.00 Miles per Gallon",
		showInLegend: false,
		toolTipContent: "<b>Weight:</b> {x}, <br><b>Fuel Economy:</b> {y}",
		dataPoints : <%out.print(request.getAttribute("list"));%>
	},{
		type: "line",
		xValueFormatString: "#,##0.00 Thousand Pounds",
		yValueFormatString: "#,##0.00 Miles per Gallon",
		showInLegend: false,
		toolTipContent: "<b>Weight:</b> {x}, <br><b>Fuel Economy:</b> {y}",
		dataPoints : <%out.print(request.getAttribute("nodes"));%>
	}]
});
chart.render();
 
}
</script>
</head>
<body>
<div id="chartContainer" style="height: 370px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>

<h1> ${dataPoints}</h1>

















</html>