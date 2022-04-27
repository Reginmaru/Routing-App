package com.example.RoutingApp.chain;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Collection;
import java.util.List;


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
    @RequestMapping( path = "api/chainsCreated")
    @GetMapping
    public List<List<String>> calculateAllRoutes(chain c){
        c =(chain) chainservice.getAllChains().toArray()[0];
        
        
        return chainservice.calculateAllRoutes(c);
    }
    @RequestMapping( path = "api/chainsCreatedTest")
    @GetMapping
    public List<List<String>> calculate(chain c){
        c =(chain) chainservice.getAllChains().toArray()[0];
        
        
        return chainservice.calculate(c);
    }
    @RequestMapping( path = "api/calculateQuickestWeight")
    @GetMapping
    public Double calculateQuickestWeight(chain c){
        c =(chain) chainservice.getAllChains().toArray()[0];
        
        
        return chainservice.calculateQuickestWeight(c);
    }
    @RequestMapping( path = "api/calculateWeightsForChains")
    @GetMapping
    public List<Double> calculateWeightsForChains(chain c){
        c =(chain) chainservice.getAllChains().toArray()[0];

        return chainservice.calculateWeightsForChains(c);
    }
}
