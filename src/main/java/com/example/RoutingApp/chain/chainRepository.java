package com.example.RoutingApp.chain;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface chainRepository
        extends JpaRepository <chain,Long>{
}
