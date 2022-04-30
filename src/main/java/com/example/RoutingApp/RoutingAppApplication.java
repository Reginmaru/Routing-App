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
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import javax.xml.XMLConstants;


@SpringBootApplication
@RestController
public class RoutingAppApplication {
	// @Override
    // protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
	// 	return builder.sources(RoutingAppApplication.class);
	// }
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
			chainrepository.save(new chain("A","E")); 
		
		}catch(ParserConfigurationException | SAXException | IOException e){
			e.printStackTrace();
		}
		};
	}
}
