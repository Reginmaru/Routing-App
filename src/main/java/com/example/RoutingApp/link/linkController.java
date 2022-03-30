package com.example.RoutingApp.link;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;


@RestController

public class linkController {

    @Autowired
    private final linkService linkservice;

    @Autowired
    linkController(linkService linkservice){
        this.linkservice = linkservice;
    }
    @RequestMapping( path = "api/startingNodes")
    @GetMapping
    public Collection<String> getStartingNodes(){
        return linkservice.getStartingNodes();
    }
    @RequestMapping( path = "api/endingNodes")
    @GetMapping
    public Collection<String> getEndingNodes(){
        return linkservice.getEndingNodes();
    }

    
}
