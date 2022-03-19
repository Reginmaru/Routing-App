package com.example.RoutingApp.node;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;


@RestController
@RequestMapping( path = "api/v1/nodes")
public class nodeController {

    private final nodeService nodeservice;

    @Autowired
    nodeController(nodeService nodeservice){
        this.nodeservice = nodeservice;
    }
    @GetMapping
	public List<node> getNodes() {
        return nodeservice.getNodes();
	}

}
