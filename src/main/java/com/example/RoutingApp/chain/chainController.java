package com.example.RoutingApp.chain;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping( path = "")
public class chainController {
    //Attaching service to controller
    @Autowired
    private final chainService chainservice;
    @Autowired
    public chainController (chainService chainservice){
        this.chainservice = chainservice;
    }

    //All chains, gonna be empty
    @RequestMapping( path = "api/chains")
    @GetMapping
    public Collection<chain> getAllChains(){
        return chainservice.getAllChains();
    }
    
    //calculate all routes, going from starting to ending node. (only 1 way)
    @RequestMapping( path = "api/oneWay")
    @GetMapping
    public List<List<String>> calculateAllRoutes(chain c){
        c =(chain) chainservice.getAllChains().toArray()[0];
        return chainservice.calculateAllRoutes(c);
    }

    //calculate possible routes (only 1 way)(not used but could be needed for more fixtures)
    @RequestMapping( path = "api/oneWay/possibleRoutes")
    @GetMapping
    public List<List<String>> calculate(chain c){
        c =(chain) chainservice.getAllChains().toArray()[0]; 
        return chainservice.calculate(c);
    }

    //Attaches and returns minimal weight to chain created.
    @RequestMapping( path = "api/calculateQuickestWeight")
    @GetMapping
    public Double calculateQuickestWeight(chain c){
        c =(chain) chainservice.getAllChains().toArray()[0]; 
        return chainservice.calculateQuickestWeight(c);
    }

    //Calculates all weights for chain, input in a chain. (not used but could be needed for more fixtures)
    @RequestMapping( path = "api/calculateWeightsForChains")
    @GetMapping
    public List<Double> calculateWeightsForChains(chain c){
        c =(chain) chainservice.getAllChains().toArray()[0];

        return chainservice.calculateWeightsForChains(c);
    }

    //Gets all the nodes and links in one single json.
    @RequestMapping( path = "api/nodesAndLinks")
    @GetMapping
    public String bothNodesAndLinks(){
        return chainservice.bothNodesAndLinks();
    }

    //This is the model used with the information of all the nodes and links to create the network graph.
    @GetMapping( path = "")
    public ModelAndView graph(){
        ModelAndView model = new ModelAndView("html","chain",chainservice.bothNodesAndLinks());
        return model;
    }
}
