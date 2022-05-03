package com.example.RoutingApp.chain;
import javax.persistence.*;

//Attaching Class to a table in postgres
@Entity
@Table
public class chain {
    @Id
    @SequenceGenerator(
        name = "chain_sequence",
        sequenceName = "chain_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "chain_sequence"
    )
    private Long id;
    private String startingPosition;
    private String endingPosition;
    private Double weight;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getStartingPosition() {
        return startingPosition;
    }
    public void setStartingPosition(String startingPosition) {
        this.startingPosition = startingPosition;
    }
    public String getEndingPosition() {
        return endingPosition;
    }
    public void setEndingPosition(String endingPosition) {
        this.endingPosition = endingPosition;
    }
    public Double getWeight() {
        return weight;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    public chain(Long id,String startingPosition,String endingPosition){
        this.id = id;
        this.startingPosition = startingPosition;
        this.endingPosition = endingPosition;
    }
    public chain(String startingPosition,String endingPosition){
        this.startingPosition = startingPosition;
        this.endingPosition = endingPosition;
    }
    public chain(){
    }
}
