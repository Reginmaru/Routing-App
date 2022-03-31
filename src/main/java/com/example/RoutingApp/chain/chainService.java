package com.example.RoutingApp.chain;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class chainService {
    
    private chainRepository chainrepository;

    @Autowired
    public chainService( chainRepository chainrepository){
        this.chainrepository = chainrepository;
    }
    public Collection<chain> getAllChains(){
        return chainrepository.findAll();
    }
}
