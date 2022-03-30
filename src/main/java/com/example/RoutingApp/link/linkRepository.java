package com.example.RoutingApp.link;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.*;


public interface linkRepository 
        extends JpaRepository<link, Long>{

                @Query("SELECT i.startingNode FROM link i")
                Collection<String> findAllStartingNodes();

                @Query("SELECT i.endingNode FROM link i")
                Collection<String> findAllEndingNodes();

                
    
}
