package com.example.practica9;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface pokemonRepository extends MongoRepository<pokemon, String> {
   
    //List <pokemon> findAll();
            
    List<pokemon> findByCaptureDateDateLessThanEqual(Date captureDate);
    
    List<pokemon> findByLevelGreaterThan(int level);
    
    List<pokemon> findByLevelBetween(int min, int max);
    
    List<pokemon> findByTypeContaining(String type);
    
    List<pokemon> findByTrainer(String trainer);
    
    List<pokemon> findByNameContaining(String keyword);

}