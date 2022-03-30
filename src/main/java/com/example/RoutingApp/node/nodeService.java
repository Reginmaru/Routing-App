package com.example.RoutingApp.node;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class nodeService {

	private nodeRepository noderepository;

	@Autowired
	public nodeService(nodeRepository noderepository) {
		this.noderepository = noderepository;
	}

    public List<node> getNodes() {	
		return noderepository.findAll();
	}
    
}
