package com.example.RoutingApp.routeXML;

import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.example.RoutingApp.link.*;
import com.example.RoutingApp.node.*;

@Entity
@Table
@XmlRootElement
public class routeXML {
    @Id
    @SequenceGenerator(
        name = "routeXML_sequence",
        sequenceName = "routeXML_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "routeXML_sequence"
    )
    private int id;
    @OneToMany(targetEntity = node.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "routeId", referencedColumnName = "id")
    private List<node> nodes;
    @OneToMany(targetEntity = link.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "routeId", referencedColumnName = "id")
    private List<link> links;

    public List<node> getNodes() {
        return nodes;
    }
    public List<link> getLinks() {
        return links;
    }
    public void setNodes(List<node> nodes) {
        this.nodes = nodes;
    }
    public void setLinks(List<link> links) {
         this.links = links;
    }
    @Override
    public String toString(){
        return "nodes" + nodes;
    }
}
