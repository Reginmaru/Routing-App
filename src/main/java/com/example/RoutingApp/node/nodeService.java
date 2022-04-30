package com.example.RoutingApp.node;
import java.util.*;

import com.google.gson.Gson;

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
	
	public String graphOfNodes(){
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		Map<Object,Object> mapOfNodes = null;

		for(int i = 0 ; i<getNodes().size(); i++){
			mapOfNodes = new HashMap<Object,Object>();
			mapOfNodes.put("x",getNodes().get(i).getX());
			mapOfNodes.put("y",getNodes().get(i).getY());
			list.add(mapOfNodes);
		}
		
		Gson gsonObj = new Gson();

		String dataPoints = gsonObj.toJson(list);
		return dataPoints;

	}
    
}
