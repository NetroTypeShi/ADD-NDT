package com.example.practica9;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    
    List<Transaction> findByIncomeIsFalse();
    
    List<Transaction> findByIncomeIsTrue();
    
    List<Transaction> findByDateGreaterThanEqual(Date date);
    
    List<Transaction> findByQuantityGreaterThan(double quantity);
    
    List<Transaction> findByQuantityBetween(double min, double max);
    
    List<Transaction> findByIncomeFalseAndDateBetween(Date startDate, Date endDate);
    
    List<Transaction> findByIncomeFalseAndQuantityLessThan(double quantity);
    
    List<Transaction> findByIncomeFalseAndQuantityGreaterThan(double quantity);
    
    List<Transaction> findByDescriptionContaining(String keyword);
}