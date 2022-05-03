package com.example.RoutingApp.node;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//Attaching to postgres
@Repository
public interface nodeRepository
        extends JpaRepository<node, Long>{
    
}
