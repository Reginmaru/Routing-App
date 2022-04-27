package com.example.RoutingApp.link;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class linkService {

    private linkRepository linkrepository;

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
    public List<link> getAllLinks(){
        return linkrepository.findAll();
    }
    public List<String> getAllStartAndEnd(){
        List<link> allLinks = linkrepository.findAll();

			List<String> sortingLinksInOrder = new ArrayList<String>();
			allLinks.forEach((l) -> {
				sortingLinksInOrder.add(l.getStartingNode() + l.getEndingNode());
			});
        return sortingLinksInOrder;
    }
}
