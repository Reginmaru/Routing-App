package com.example.RoutingApp.link;

public class link {
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
    
    
    
}
