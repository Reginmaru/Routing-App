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
import java.io.InputStream;

import com.example.RoutingApp.link.link;
import com.example.RoutingApp.node.*;
import com.example.RoutingApp.link.linkRepository;
import com.example.RoutingApp.routeXML.routeXML;

import org.springframework.boot.ApplicationRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.xml.XMLConstants;


@SpringBootApplication
@RestController
public class RoutingAppApplication {

	private static final Logger log = LoggerFactory.getLogger(RoutingAppApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(RoutingAppApplication.class, args);
	}

	@Bean
	ApplicationRunner init (nodeRepository noderepository, linkRepository linkrepository){
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

			for ( int i = 0; i < listOfNodes.getLength(); i++){
				Node n = listOfNodes.item(i);
				if ( n.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) n;

					String nameNode = element.getAttribute("name");
					String x = element.getElementsByTagName("x").item(0).getTextContent();
					String y = element.getElementsByTagName("y").item(0).getTextContent();
					System.out.println(nameNode);
					System.out.println(x);
					System.out.println(y);
					noderepository.save(new node(nameNode, Double.parseDouble(x), Double.parseDouble(y)));
				}

			}
		}catch(ParserConfigurationException | SAXException | IOException e){
			e.printStackTrace();
		}

		};

	}

}
