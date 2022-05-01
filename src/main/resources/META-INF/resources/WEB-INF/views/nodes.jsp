<%@ page contentType="text/html;charset=ISO-8859-1" language="java" pageEncoding="ISO-8859-1" %>
<script src="https://cdn.anychart.com/releases/8.8.0/js/anychart-core.min.js"></script>
<script src="https://cdn.anychart.com/releases/8.8.0/js/anychart-graph.min.js"></script>
<script src="https://cdn.anychart.com/releases/8.8.0/js/anychart-data-adapter.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta charset="ISO-8859-1">
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="src.main.java.com.example.RoutingApp.node.nodeService.*"%>
<%@ page import="src.main.java.com.example.RoutingApp.chain.chainService.*"%>
<style type="text/css">
   text {
      font: 10pt sans-serif;
      -webkit-user-select: none;
      -moz-user-select: none;
      user-select: none;
   }

   .link {
      stroke: black;
      stroke-width: 1px;
      opacity: 0.25;
   }

   .link-label {
      opacity: 0.5;
   }

   .node {
      cursor: pointer;
   }
   

#scalesvg { 
    margin:0 auto; }
</style>


<!DOCTYPE html>
<meta charset="utf-8">
<script src="https://d3js.org/d3.v4.js"></script>

<div class="svg-container" id = "scalesvg">
	<svg width="960" height="600" ></svg>
</div>
<script>
var data = <%out.print(request.getAttribute("chain")); %>
var nameToId = {}
for(let i=0; i < data.nodes.length; i++){
	nameToId[data.nodes[i].link_name] = data.nodes[i].id -1;
}				
for(let i=0; i < data.links.length; i++){
	data.links[i].source = nameToId[data.links[i].startingNode];
	data.links[i].target = nameToId[data.links[i].endingNode] ;
}

var svg = d3.select("svg"),
    width = +svg.attr("width"),
    height = +svg.attr("height");

var simulation = d3.forceSimulation()
    .force("link", d3.forceLink().id(function(d) { return d.id; }))
    .force("charge", d3.forceManyBody().strength(-1000))
    .force("center", d3.forceCenter(width / 2, height / 2));

	var link = svg.append("g")
                .style("stroke", "#aaa")
                .selectAll("line")
                .data(data.links)
                .enter().append("line");

	var linkLabels = svg.append("g")
      .attr("class", "linkLabel")
      .selectAll("text")
      .data(data.links)
      .enter().append("text")
        .attr("class", "label")
        .text(function(d) { return d.weight; });

  var node = svg.append("g")
            .attr("class", "nodes")
  .selectAll("circle")
            .data(data.nodes)
  .enter().append("circle")
          .attr("r", 6)
          .call(d3.drag()
            //   .on("start", dragstarted)
              .on("drag", dragged));
            //   .on("end", dragended));
  
  var label = svg.append("g")
      .attr("class", "labels")
      .selectAll("text")
      .data(data.nodes)
      .enter().append("text")
        .attr("class", "label")
        .text(function(d) { return d.link_name; });

  simulation
      .nodes(data.nodes)
      .on("tick", ticked);

	simulation.force("link", d3.forceLink(data.links));
//	.on("tick",updateLinkLabels);

  function ticked() {
    link
        .attr("x1", function(d) { return d.source.x; })
        .attr("y1", function(d) { return d.source.y; })
        .attr("x2", function(d) { return d.target.x; })
        .attr("y2", function(d) { return d.target.y; });

    node
         .attr("r", 20)
         .style("fill", "#d9d9d9")
         .style("stroke", "#969696")
         .style("stroke-width", "1px")
         .attr("cx", function (d) { return d.x+6; })
         .attr("cy", function(d) { return d.y-6; });
    
    label
    		.attr("x", function(d) { return d.x; })
            .attr("y", function (d) { return d.y; })
            .style("font-size", "20px").style("fill", "#4393c3");
			
    linkLabels.attr("x", d => ((d.source.x + d.target.x) / 2))
        .attr("y", d => ((d.source.y + d.target.y) / 2));
  }


// function dragstarted(d) {
//   if (!d3.event.active) simulation.alphaTarget(0.3).restart()
//   simulation.fix(d);
// }
function dragged(event,d) {
  d.fx = event.x;
  d.fy = event.y;
}

// function dragged(d) {
//   simulation.fix(d, d3.event.x, d3.event.y);
// }

// function dragended(d) {
//   if (!d3.event.active) simulation.alphaTarget(0);
//   simulation.unfix(d);
// }

</script>

</html>
