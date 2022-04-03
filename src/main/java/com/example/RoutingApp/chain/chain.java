package com.example.RoutingApp.chain;

import com.example.RoutingApp.link.*;
import java.util.List;

import javax.persistence.*;


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
    @ManyToMany
    private List<link> chainListLinks;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<link> getChainListLinks() {
        return chainListLinks;
    }
    public void setChainListLinks(List<link> chainListLinks) {
        this.chainListLinks = chainListLinks;
    }
    public chain(Long id, List<link> chainListLinks){
        this.id = id;
        this.chainListLinks = chainListLinks;
    }
    public chain(List<link> chainListLinks){
        this.chainListLinks = chainListLinks;
    }
    public chain(){
    }
}
