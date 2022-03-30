package com.example.RoutingApp.chain;
import com.example.RoutingApp.link.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class chain {
    @Autowired
    private linkRepository linkrepository;

    private  List<List<link>> allPossibleChains;
    private List<link> allLinks;
    public static void main(String [] args){
        chain chain1 = new chain();
        
     }
    public void setAllLinks(){
         this.allLinks = linkrepository.findAll();
    }
    public void addLinksToChain(){
        for (int i = 0; i < linkrepository.findAll().size(); i ++){
            
            //allPossibleChains.get(i).add()
        }
    }
}
