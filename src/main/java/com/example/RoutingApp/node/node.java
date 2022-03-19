package com.example.RoutingApp.node;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

@Entity
@Table
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
    private String name;
    private Double x;
    private Double y;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
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
    public void setName(String name) {
        this.name = name;
    }
    public void setX(Double x) {
        this.x = x;
    }
    public void setY(Double y) {
        this.y = y;
    }
    public node( String name,
                    Double x,
                    Double y){
                        this.name = name;
                        this.x = x;
                        this.y = y;
                    }
    public node(    Long id,
                    String name,
                    Double x,
                    Double y){
                        this.id = id;
                        this.name = name;
                        this.x = x;
                        this.y = y;
                    }
    @Override
    public String toString(){
        return "node{" +
        "id=" + id +
        ", name = '" + name + '\'' +
        "x =" + x +
        "y =" + y +
        '}';
    }
}
