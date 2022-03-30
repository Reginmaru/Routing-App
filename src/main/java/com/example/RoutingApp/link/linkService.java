package com.example.RoutingApp.link;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class linkService {

    private linkRepository linkrepository;
    private List<List<link>> allPossibleChains;
    private List<link> oneLink;

    @Autowired
    public linkService(linkRepository linkrepository) {
        this.linkrepository = linkrepository;
    }
    public Collection<String> getStartingNodes(){
        
        return linkrepository.findAllStartingNodes();
    }
    public Collection<String> getEndingNodes(){        
        return linkrepository.findAllEndingNodes();
    }
}
