package com.example.RoutingApp.node;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.GeneratedValue;
//Attaching to postgres.
@Entity
@Table
@XmlRootElement //Associating to XML elements.
public class node {
    @Id
    @SequenceGenerator(
        name = "node_sequence",
        sequenceName = "node_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "node_sequence"
    )

    private Long id;
    private String link_name;
    private Double x;
    private Double y;

    public Long getId() {
        return id;
    }
    public String getName() {
        return link_name;
    }
    public Double getX() {
        return x;
    }
    public Double getY() {
        return y;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String link_name) {
        this.link_name = link_name;
    }
    public void setX(Double x) {
        this.x = x;
    }
    public void setY(Double y) {
        this.y = y;
    }
    public node( String link_name,
                    Double x,
                    Double y){
                        this.link_name = link_name;
                        this.x = x;
                        this.y = y;
                    }
    public node(    Long id,
                    String link_name,
                    Double x,
                    Double y){
                        this.id = id;
                        this.link_name = link_name;
                        this.x = x;
                        this.y = y;
                    }
    public node(){
    }
}
