package com.example.RoutingApp.link;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
//Needed to attach to postgres
@Entity
@Table
@XmlRootElement // Needed to identify XML part.
public class link {
    @Id
    @SequenceGenerator(
        name = "link_sequence",
        sequenceName = "link_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "link_sequence"
    )
    private Long id;
    private String startingNode;
    private String endingNode;
    private Double weight;

    public Long getId() {
        return id;
    }
    public String getStartingNode() {
        return startingNode;
    }
    public String getEndingNode() {
        return endingNode;
    }
    public Double getWeight() {
        return weight;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setStartingNode(String startingNode) {
        this.startingNode = startingNode;
    }
    public void setEndingNode(String endingNode) {
        this.endingNode = endingNode;
    }
    public link(String startingNode,
                    String endingNode,
                    Double weight)
                    {
                        this.startingNode = startingNode;
                        this.endingNode = endingNode;
                        this.weight = weight;
                    }
    public link(    Long id,
                    String startingNode,
                    String endingNode,
                    Double weight)
                    {
                        this.id = id;
                        this.startingNode = startingNode;
                        this.endingNode = endingNode;
                        this.weight = weight;
                    }
    public link(){
    }
}
