package com.example.RoutingApp.chain;
import java.util.*;

import com.example.RoutingApp.link.link;
import com.example.RoutingApp.link.linkService;
import com.example.RoutingApp.node.nodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class chainService {
    
    private chainRepository chainrepository;
    @Autowired
    private final linkService linkservice;
    @Autowired
    private final nodeService nodeservice;

    @Autowired
    public chainService( chainRepository chainrepository, linkService linkservice, nodeService nodeservice){
        this.chainrepository = chainrepository;
        this.linkservice = linkservice;
        this.nodeservice = nodeservice;
        
    }
    
    
    public Collection<chain> getAllChains(){
        return chainrepository.findAll();
    }
    public List<List<String>> calculateAllRoutes(chain c){
        List<link> allLinks = linkservice.getAllLinks();
        List<String> sortedLinks = new ArrayList<String>();
			allLinks.forEach((l) -> {
				sortedLinks.add(l.getStartingNode() + l.getEndingNode());
			});
        List<List<String>> all = new ArrayList<List<String>>();
        for (int j = 0; j<sortedLinks.size(); j++){
            if(c.getStartingPosition().charAt(0) == sortedLinks.get(j).charAt(0)){
                List<String> one = new ArrayList<String>();
                one.add(sortedLinks.get(j));
                all.add(one);
            }
        }
        for (int i = 0 ; i<all.size();i++){
            for( int j = 0 ;j<sortedLinks.size();j++){
                if(all.get(i).get(all.get(i).size()-1).charAt(1) == sortedLinks.get(j).charAt(0)){
                    List <String> one = new ArrayList<String>();
                    for(int k = 0 ; k<all.get(i).size() ; k ++){
                        one.add(all.get(i).get(k));
                    }
                    one.add(sortedLinks.get(j));
                    all.add(one);
                    
                }
            }
        }
        return all;
    }

    public List<List<String>> calculate(chain c){
        List<link> allLinks = linkservice.getAllLinks();
        List<String> sortedLinks = new ArrayList<String>();
			allLinks.forEach((l) -> {
				sortedLinks.add(l.getStartingNode() + l.getEndingNode());
			});
        List<List<String>> all = new ArrayList<List<String>>();

        for (int j = 0; j<sortedLinks.size(); j++){
            if(c.getStartingPosition().charAt(0) == sortedLinks.get(j).charAt(0)){
                List<String> one = new ArrayList<String>();
                one.add(sortedLinks.get(j));
                all.add(one);
            }
        }
        for (int i = 0 ; i<all.size();i++){
            for( int j = 0 ;j<sortedLinks.size();j++){
                if(all.get(i).get(all.get(i).size()-1).charAt(1) == sortedLinks.get(j).charAt(0)){
                    List <String> one = new ArrayList<String>();
                    for(int k = 0 ; k<all.get(i).size() ; k ++){
                        one.add(all.get(i).get(k));
                    }
                    one.add(sortedLinks.get(j));
                    all.add(one);
                    
                }
            }
        }
        List<List<String>> possibleRoutes = new ArrayList<List<String>>();
        for (int i = 0 ; i < all.size(); i++){
            if ( all.get(i).get(all.get(i).size()-1).charAt(1) == c.getEndingPosition().charAt(0) ){
                possibleRoutes.add(all.get(i));
            }
        }

        return possibleRoutes;
    }

    public Double calculateQuickestWeight(chain c){
        List<Double> weights = new ArrayList<Double>();
        List<link> allLinks = linkservice.getAllLinks();
        List<List<String>> listOfLinks = calculate(c);
        for ( int i = 0 ; i<listOfLinks.size() ; i++){
            Double weight = 0.0;
            for( int k = 0 ; k<listOfLinks.get(i).size() ; k++){
                for ( int j = 0 ; j<allLinks.size() ; j ++){
                    if( listOfLinks.get(i).get(k).equals(allLinks.get(j).getStartingNode() + allLinks.get(j).getEndingNode())){
                        weight +=allLinks.get(j).getWeight();
                    }
                }
            }
            weights.add(weight);
        }
        c.setWeight(Collections.min(weights));
        
        return c.getWeight();
    }
    public List<Double> calculateWeightsForChains(chain c){
        List<Double> weights = new ArrayList<Double>();
        List<link> allLinks = linkservice.getAllLinks();
        List<List<String>> listOfLinks = calculate(c);
        for ( int i = 0 ; i<listOfLinks.size() ; i++){
            Double weight = 0.0;
            for( int k = 0 ; k<listOfLinks.get(i).size() ; k++){
                for ( int j = 0 ; j<allLinks.size() ; j ++){
                    if( listOfLinks.get(i).get(k).equals(allLinks.get(j).getStartingNode() + allLinks.get(j).getEndingNode())){
                        weight +=allLinks.get(j).getWeight();
                    }
                }
            }
            weights.add(weight);
        }
        return weights;
    }

}
