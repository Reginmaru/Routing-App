package com.example.RoutingApp.routeXML;

import java.util.ArrayList;
import com.example.RoutingApp.link.*;
import com.example.RoutingApp.node.*;
public class routeXML {
    private ArrayList<node> nodes;
    private ArrayList<link> links;

    public ArrayList<node> getNodes() {
        return nodes;
    }
    public ArrayList<link> getLinks() {
        return links;
    }
    public void setNodes(ArrayList<node> nodes) {
        this.nodes = nodes;
    }
    public void setLinks(ArrayList<link> links) {
        this.links = links;
    }
}
