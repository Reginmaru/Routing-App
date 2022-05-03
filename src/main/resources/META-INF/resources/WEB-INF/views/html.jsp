<%@ page contentType="text/html;charset=ISO-8859-1" language="java" pageEncoding="ISO-8859-1" %>
<script src="https://cdn.anychart.com/releases/8.8.0/js/anychart-core.min.js"></script>
<script src="https://cdn.anychart.com/releases/8.8.0/js/anychart-graph.min.js"></script>
<script src="https://cdn.anychart.com/releases/8.8.0/js/anychart-data-adapter.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/d3-tip/0.7.1/d3-tip.min.js"></script>
<script src="https://d3js.org/d3.v4.js"></script>
<script src="https://unpkg.com/d3-v6-tip@1.0.6/build/d3-v6-tip.js"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>

<style type="text/css">
/* Style for all graph and tips */
#scalesvg { 
    margin:0 auto; }
.d3-tip {
  line-height: 1;
  font-weight: bold;
  padding: 12px;
  background: rgba(0, 0, 0, 0.8);
  color: #fff;
  border-radius: 2px;
}

/* Creates a small triangle extender for the tooltip */
.d3-tip:after {
  box-sizing: border-box;
  display: inline;
  font-size: 10px;
  width: 100%;
  line-height: 1;
  color: rgba(0, 0, 0, 0.8);
  content: "\25BC";
  position: absolute;
  text-align: center;
}

/* Style northward tooltips differently */
.d3-tip.n:after {
  margin: -1px 0 0 0;
  top: 100%;
  left: 0;
}
</style>

<!DOCTYPE html>
<meta charset="utf-8">
<html xmlns:th="https://www.thymeleaf.org">
    <body>
        <%-- Components of Page --%>
        <div id = "scalesvg">Click 2 Nodes to calculate Fastest Route
        </div>
        <div class="svg-container" id = "scalesvg">
        	<svg width="960" height="500" ></svg>
        </div>
        <p id="buttonNodes" class="showNodes" >SHOW STATS</p>
        <p>Weight of total distance:</p>
         <div class="showWeight" id="showWeight">
            <p></p>
        </div>
        <p>All the nodes in chain:</p>
        <div class="showNodes" id="showNodes">
            <p></p>
        </div>
        <p>Number of links in chain:</p>
        <div class="showNLinks" id="showNLinks">
            <p></p>
        </div>

        <script>
            // Getting Data from ModelAndView
            var data = <%out.print(request.getAttribute("chain")); %>
            // To match graph attribute with data. 
            var nameToId = {}
            for(let i=0; i < data.nodes.length; i++){
            	nameToId[data.nodes[i].link_name] = data.nodes[i].id -1;
            }				
            for(let i=0; i < data.links.length; i++){
            	data.links[i].source = nameToId[data.links[i].startingNode];
            	data.links[i].target = nameToId[data.links[i].endingNode] ;
            }
            // Creating graph area
            var svg = d3.select("svg"),
                width = +svg.attr("width"),
                height = +svg.attr("height");
            // To show information of links and nodes
            var tip = d3.tip()
                .attr('class', 'd3-tip')
                .offset([-10, 0])
                .html(function(d) {
                    return "<strong>X:</strong> <span style='color:green'>"
                        + d.x + "</span><strong> Y:</strong> <span style='color:green'>"
                        + d.y + "</span>"
                })

            var tiplink = d3.tip()
                .attr('class', 'd3-tip')
                .offset([-10, 0])
                .html(function(d) {
                    return "<span style='color:white;font-family: Arial, Helvetica, sans-serif'>"
                    + d.startingNode + "-" 
                    + d.endingNode + "</span>";
                })

            // Calling those functions of information
            svg.call(tiplink);
            svg.call(tip);

            // To create relative distance between nodes and therefore size of links
            var simulation = d3.forceSimulation()
                .force("link", d3.forceLink().id(function(d) { return d.id; }))
                .force("charge", d3.forceManyBody().strength(-1000))
                .force("center", d3.forceCenter(width / 2, height / 2));

            //Creating links
        	var link = svg.append("g")
                .style("stroke", "#aaa")
                .selectAll("line")
                .data(data.links)
                .enter().append("line")
                

            //Creating linkLabels
        	var linkLabels = svg.append("g")
                .attr("class", "linkLabel")
                .selectAll("text")
                .data(data.links)
                .enter().append("text")
                .attr("dy", 5)
                .attr("class", "label")
                .text(function(d) { return d.weight; })
                .on('mouseover', tiplink.show)
                .on('mouseout', tiplink.hide);

            //Creating nodes
            var node = svg.append("g")
                .attr("class", "nodes")
                .selectAll("circle")
                .data(data.nodes)
                .enter().append("circle")
                .attr("r", 6)
                .on('mouseover', tip.show)
                .on('mouseout', tip.hide)

            //Creating node Labels
            var label = svg.append("g")
                .attr("class", "labels")
                .selectAll("text")
                .data(data.nodes)
                .enter().append("text")
                .attr("class", "label")
                .text(function(d) { return d.link_name; });

            //Events
            simulation
                .nodes(data.nodes)
                .on("tick", ticked);

        	simulation.force("link", d3.forceLink(data.links));


            var weights = [] // To be able to grab this value from algorithm and put into onclick div. (scope)
            var clickedNodes = []; // Array that will contain the nodes we clicked

            //If ticked apply these functions.
            function ticked() {
                link
                    .attr("x1", function(d) { return d.source.x; })
                    .attr("y1", function(d) { return d.source.y; })
                    .attr("x2", function(d) { return d.target.x; })
                    .attr("y2", function(d) { return d.target.y; })
        		    .on("click", function(d){
        		    	d3.select(this)
        		    .attr("stroke","#234F1E")
                    });

                node
                    .attr("r", 20)
                    .style("fill", "#d9d9d9")
        		    .style("stroke", "#969696")
                    .style("stroke-width", "1px")
                    .attr("cx", function (d) { return d.x+6; })
                    .attr("cy", function(d) { return d.y-6; }) 
        		    .on("click", function(d){
        			    click(d,d3.select(this))})

        	    label
            		.attr("x", function(d) { return d.x; })
                    .attr("y", function (d) { return d.y; })
                    .style("font-size", "20px").style("fill", "#4393c3");

                linkLabels.attr("x", d => ((d.source.x + d.target.x) / 2))
                    .attr("y", d => ((d.source.y + d.target.y) / 2));
            }
            //Click function for 2 clicks and start algorithm to find quickest path, weight.
            function click(d,d3){

        		var changedThisClick = false

                // adding colour to check it has been clicked.
        		if( d3.style("fill") == "rgb(217, 217, 217)"){
        			if(!clickedNodes.includes(d.link_name)){
        				clickedNodes.push(d.link_name)
        			}
        			d3
        			.style("fill","#0000FF")
        			var changedThisClick = true
        		}
        		if( d3.style("fill") == "rgb(0, 0, 255)" & changedThisClick == false){
        			clickedNodes.pop(d.link_name)
        			d3
        			.style("fill","#d9d9d9")
        		}

                //if when 2 clicks have happened start algorithm
        		if(clickedNodes.length == 2){
                    //create array with just [startingNode[i],endingNode[i]] 
                    const sortedLinks = [];
                    for(let i = 0;i<data.links.length; i++){
                        sortedLinks.push(data.links[i].startingNode + data.links[i].endingNode)
                    }
                    
                    const all = []; // all [array(1),array(2),..] 
                    for( let j = 0 ; j<sortedLinks.length ; j ++){
                        if(clickedNodes[0] == sortedLinks[j].charAt(0) || clickedNodes[0] == sortedLinks[j].charAt(1) ){

                            //This creates an array which goes into all, this selects the first link.
                            const one = [];
                            one.push(sortedLinks[j]);
                            all.push(one);
                        }
                    }
                    //Adds more links to starting links.
                    for ( let k = 0 ; k< all.length; k++){
                        for (let h = 0 ; h<sortedLinks.length; h++){

                            if(all[k][all[k].length-1].charAt(1) == sortedLinks[h].charAt(0) 
                                || all[k][all[k].length-1].charAt(0) == sortedLinks[h].charAt(1)
                                || all[k][all[k].length-1].charAt(1) == sortedLinks[h].charAt(1) 
                                || all[k][all[k].length-1].charAt(0) == sortedLinks[h].charAt(0)
                                ){
                                
                                /* Creates a new array(which all information of all[k]) each time there is a match,
                                To allow for multiple paths. */
                                const one = []

                                for(let l = 0 ; l<all[k].length;l++){
                                        one.push(all[k][l])
                                }
                                one.push(sortedLinks[h])

                                //Stopping same path to be taken again.
                                let result = false;
                                const s = new Set(one);
                                if(one.length == s.size){
                                   result = true;
                                }
                                if(result == true){
                                    all.push(one)
                                }
                            }
                        }
                    }
                    
                    //from all to possibleRoutes to second clickedNode.
                    const possibleRoutes = [];
                    for(let i = 0; i<all.length; i++){
                        if(all[i][all[i].length-1].charAt(1) == clickedNodes[1].charAt(0) 
                              || all[i][all[i].length-1].charAt(0) == clickedNodes[1].charAt(0)
                            ){
                            possibleRoutes.push(all[i])
                        }
                    }

                    //Calculating weight of chain, as well as array of nodes which the chain has.
                    let arrayOfWeights = []
                    let minWeight = 1000000
                    for ( let i = 0 ; i<possibleRoutes.length; i++){
                        let weightAdd = 0.0;
                        for(let k = 0 ; k<possibleRoutes[i].length; k++){
                            for( let j = 0 ; j<data.links.length; j++){
                                if( possibleRoutes[i][k] == data.links[j].startingNode + data.links[j].endingNode){
                                    weightAdd += data.links[j].weight
                                }
                            }
                        }  
                        if( weightAdd < minWeight){
                            minWeight = weightAdd
                            weights = []
                            weights.push(minWeight)
                            weights.push(possibleRoutes[i])
                            for(let k = 0; k < possibleRoutes.length; k++){
                                weights.push(possibleRoutes[i][k])
                            }      
                        }
                    }

                    //Changing the colour of link to display route.
                    let routeIds = []
                    for (let i = 0 ; i<data.links.length; i++){
                        for (let j =1; j<weights.length; j++){ // 1 because first is the weight
                            if(data.links[i].startingNode+data.links[i].endingNode == weights[j]){
                                routeIds.push(data.links[i].index)
                            }
                        }
                    }
                    for(let i = 0; i < routeIds.length; i++){
                        link.filter(function(d, j){ // i is the index
                            return j === routeIds[i];
                        })
                        .attr("stroke","#00FF00")
                    }
                    //Reset
        			clickedNodes = []
        		}
            };
        //Stats -> creating 
        const showWeight = document.getElementById("showWeight");
        const buttonNodes = document.getElementById("buttonNodes");
        const showNodes = document.getElementById("showNodes");
        const showNLinks = document.getElementById("showNLinks");

        buttonNodes.onclick = function nodeWeightFunction() {
            const setOfNodes = Array.from(new Set(weights[1]
                                        .join('')
                                        .split('')))

            showNodes.textContent = setOfNodes;
            showWeight.textContent = weights[0];
            showNLinks.textContent = setOfNodes.length-1;
        };

        </script>
    </body>  
</html>
