package com.example.RoutingApp.link;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class linkService {
    //Attaching to repository
    private linkRepository linkrepository;
    
    @Autowired
    public linkService(linkRepository linkrepository) {
        this.linkrepository = linkrepository;
    }

    //Fetches all startingNodes
    public Collection<String> getStartingNodes(){
        return linkrepository.findAllStartingNodes();
    }

    //Fetches all endingNodes
    public Collection<String> getEndingNodes(){        
        return linkrepository.findAllEndingNodes();
    }

    //Fetches all links
    public List<link> getAllLinks(){
        return linkrepository.findAll();
    }
    
    //Fetches all links and returns starting and ending nodes.
    public List<String> getAllStartAndEnd(){
        List<link> allLinks = linkrepository.findAll();

			List<String> sortingLinksInOrder = new ArrayList<String>();
			allLinks.forEach((l) -> {
				sortingLinksInOrder.add(l.getStartingNode() + l.getEndingNode());
			});
        return sortingLinksInOrder;
    }
}
