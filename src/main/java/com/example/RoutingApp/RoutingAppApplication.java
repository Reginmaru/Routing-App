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
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;


import com.example.RoutingApp.chain.chain;
import com.example.RoutingApp.chain.chainRepository;
import com.example.RoutingApp.link.link;
import com.example.RoutingApp.node.*;
import com.fasterxml.jackson.databind.util.JSONPObject;
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
			List<link> allLinks = linkrepository.findAll();
			Collection<String> allStartingNodes = linkrepository.findAllStartingNodes();
			Collection<String> allEndingNodes = linkrepository.findAllEndingNodes();
			Collection<String> allIds = linkrepository.findAllIds();

			for ( int i = 0; i< allLinks.size(); i++){
				List<link> t = new ArrayList<link>();
					for ( int j = 0; j< allLinks.size(); j++){
						if (allStartingNodes.toArray()[i].equals( allEndingNodes.toArray()[j]) ||
						allEndingNodes.toArray()[i].equals(allStartingNodes.toArray()[j]) ||
						allEndingNodes.toArray()[i].equals(allEndingNodes.toArray()[j]) ||
						allStartingNodes.toArray()[i].equals(allStartingNodes.toArray()[j])){
							if(!t.contains(allLinks.get(i))){
								t.add(allLinks.get(i));
							}
							if(!t.contains(allLinks.get(j))){
								t.add(allLinks.get(j));
							}
						}
					}

				chainrepository.save(new chain(t));
			}
		}catch(ParserConfigurationException | SAXException | IOException e){
			e.printStackTrace();
		}

		};

	}

}
