package com.example.RoutingApp;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;


import com.example.RoutingApp.chain.chain;
import com.example.RoutingApp.chain.chainRepository;
import com.example.RoutingApp.link.link;
import com.example.RoutingApp.node.*;
import com.example.RoutingApp.link.linkRepository;

import org.springframework.boot.ApplicationRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import javax.xml.XMLConstants;


@SpringBootApplication
@RestController
public class RoutingAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(RoutingAppApplication.class, args);
	}

	@Bean
	ApplicationRunner init (nodeRepository noderepository, linkRepository linkrepository, chainRepository chainrepository){
		return args -> {
			final String FILENAME = "graph.xml";
			

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			try{
			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(FILENAME));
			doc.getDocumentElement().normalize();
			System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
         	System.out.println("------");

			NodeList listOfNodes = doc.getElementsByTagName("node");
			NodeList listOfLinks = doc.getElementsByTagName("link");
			

			for ( int i = 0; i < listOfNodes.getLength(); i++){
				Node n = listOfNodes.item(i);
				if ( n.getNodeType() == Node.ELEMENT_NODE) {
					Element elementn = (Element) n;

					String nameNode = elementn.getAttribute("name");
					String x = elementn.getElementsByTagName("x").item(0).getTextContent();
					String y = elementn.getElementsByTagName("y").item(0).getTextContent();
					noderepository.save(new node(nameNode, Double.parseDouble(x), Double.parseDouble(y)));
				}
			}
			for ( int i = 0; i< listOfLinks.getLength(); i ++){
				Node l = listOfLinks.item(i);
				if ( l.getNodeType() == Node.ELEMENT_NODE) {
					Element elementl = (Element) l;

					String startingNode = elementl.getAttribute("startingNode");
					String endingNode = elementl.getAttribute("endingNode");
					String weight = elementl.getElementsByTagName("weight").item(0).getTextContent();
					linkrepository.save(new link(startingNode, endingNode, Double.parseDouble(weight)));
				}
			}
//go through all the links.tostring() order then in alphabetical, then foreach add() link (in alphabetical order) if a link has either a starting node or ending node the same as all the other ones in the list.
	//1. Ordered, I noticed I have more than one of the same so when I create loop I have to add them first and delete duplicate.		
			List<link> allLinks = linkrepository.findAll();

			List<String> sortingLinksInOrder = new ArrayList<String>();
			allLinks.forEach((l) -> {
				sortingLinksInOrder.add(l.getStartingNode() + l.getEndingNode());
			});
			System.out.println(sortingLinksInOrder);
			//Create new List of Lists of starting and ending nodes.
			List<List<String>> sortingIntoChains = new ArrayList<List<String>>();
			for( int i = 0 ; i < sortingLinksInOrder.size(); i ++ ){
				//Create a tempList for saving chains into big chain
				List<String> t = new ArrayList<String>();
					for ( int j = 0 ; j < sortingLinksInOrder.size(); j ++){
						//This adds all the ones with matching starting nodes but i have to continue until chain is full as well as delete duplicates
					if ( !t.contains(sortingLinksInOrder.get(i))){
						t.add(sortingLinksInOrder.get(i));
					}
					if(i != j && sortingLinksInOrder.get(i).charAt(0) == sortingLinksInOrder.get(j).charAt(0)){
						
						t.add(sortingLinksInOrder.get(j));
					}
					
				}
				sortingIntoChains.add(t);

			}
			System.out.println(sortingIntoChains);
















			// List<List<link>> tempChain = new ArrayList<List<link>>();
			// for ( int i = 0; i< allLinks.size(); i++){
			// 	List<link> t = new ArrayList<link>();
				
			// 	for ( int j = 0; j< allLinks.size(); j++){
			// 		if (allStartingNodes.toArray()[i].equals( allEndingNodes.toArray()[j]) ||
			// 		allEndingNodes.toArray()[i].equals(allStartingNodes.toArray()[j]) ||
			// 		allEndingNodes.toArray()[i].equals(allEndingNodes.toArray()[j]) ||
			// 		allStartingNodes.toArray()[i].equals(allStartingNodes.toArray()[j])){
			// 			if(!t.contains(allLinks.get(i))){
			// 				t.add(allLinks.get(i));
			// 			}
			// 			if(!t.contains(allLinks.get(j))){
			// 				t.add(allLinks.get(j));
			// 			}
			// 		}
			// 	}
			// 	tempChain.add(t);
			// }
			//System.out.println(tempChain);
			
		

			// List<List<link>> q = new ArrayList<List<link>>();
			// for ( int k = 0 ; k < tempChain.size(); k++){
			// 	List<List<link>> t = new ArrayList<List<link>>();
			// 	for( int l = 0 ; l < tempChain.size(); l++){
			// 		for( int m = 0 ; m < tempChain.get(k).size(); m++){	
			// 			for( int n = 0 ; n < tempChain.get(l).size(); n++){		
			// 				if(tempChain.get(k).get(m).toString().charAt(0) == tempChain.get(l).get(n).toString().charAt(0) ||
			// 				tempChain.get(k).get(m).toString().charAt(0) == tempChain.get(l).get(n).toString().charAt(1) ||
			// 				tempChain.get(k).get(m).toString().charAt(1) == tempChain.get(l).get(n).toString().charAt(0) ||
			// 				tempChain.get(k).get(m).toString().charAt(1) == tempChain.get(l).get(n).toString().charAt(1)
			// 				){		
			// 					t.add(tempChain.get(k));
			// 					t.add(tempChain.get(l));
			// 				}
			// 			}	
			// 			//OK, need 1 chain check through all of its char (ie [0] or [1]), then compare to all the other char, if there is a char the
			// 			//same to the original( ie [0] or [1]). If this is true add to both to a List<link> and delete duplicates. Check next chain
			// 		}
			// 	}
			// 	q= t;
			// // }	
			// System.out.println(q);
			// System.out.println("#########");
			// System.out.println(tempChain);
		}catch(ParserConfigurationException | SAXException | IOException e){
			e.printStackTrace();
		}
		};
	}
}
