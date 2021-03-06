package com.example.RoutingApp.link;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.*;
//postgres queries
public interface linkRepository 
        extends JpaRepository<link, Long>{
                //Specific queries
                @Query("SELECT i.startingNode FROM link i")
                Collection<String> findAllStartingNodes();

                @Query("SELECT i.endingNode FROM link i")
                Collection<String> findAllEndingNodes();

                @Query("SELECT i.id FROM link i")
                Collection<String> findAllIds();

                
    
}
