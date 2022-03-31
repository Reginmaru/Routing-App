package com.example.RoutingApp.chain;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Collection;


@RestController
public class chainController {

    @Autowired
    private final chainService chainservice;

    @Autowired
    public chainController (chainService chainservice){
        this.chainservice = chainservice;
    }
    @RequestMapping( path = "api/chains")
    @GetMapping
    public Collection<chain> getAllChains(){
        return chainservice.getAllChains();
    }


}
