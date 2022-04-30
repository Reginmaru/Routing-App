package com.example.RoutingApp.node;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;


@RestController
@RequestMapping( path = "")
public class nodeController {

    @Autowired
    private final nodeService nodeservice;

    @Autowired
    nodeController(nodeService nodeservice){
        this.nodeservice = nodeservice;
    }
    @RequestMapping( path = "nodes")
    @GetMapping
	public List<node> getNodes() {
        return nodeservice.getNodes();
	}
    @GetMapping( path = "nodesGraph")
    public ModelAndView graphOfNodes(){
        ModelAndView model = new ModelAndView("nodes", "list", nodeservice.graphOfNodes());
        return model;
    }
    @RequestMapping( path = "listOfNodes")
    @GetMapping
    public String listOfNodes(){
        return nodeservice.graphOfNodes();
    }

}
