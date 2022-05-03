package com.example.RoutingApp.link;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;



@RestController
public class linkController {
    //Attaching controller to service.
    @Autowired
    private final linkService linkservice;

    @Autowired
    linkController(linkService linkservice){
        this.linkservice = linkservice;
    }

    //Getting all startingNodes
    @RequestMapping( path = "api/links/startingNodes")
    @GetMapping
    public Collection<String> getStartingNodes(){
        return linkservice.getStartingNodes();
    }

    //Getting all endingNodes
    @RequestMapping( path = "api/links/endingNodes")
    @GetMapping
    public Collection<String> getEndingNodes(){
        return linkservice.getEndingNodes();
    }

    //Getting all links
    @RequestMapping( path = "api/links")
    @GetMapping
    public List<link> getAllLinks(){
        return linkservice.getAllLinks();
    }
    
    //Getting all links with just starting and endingnodes list.
    @RequestMapping( path = "api/startEnd")
    @GetMapping
    public List<String> getStartAndEnd(){
        return linkservice.getAllStartAndEnd();
    }  
}
