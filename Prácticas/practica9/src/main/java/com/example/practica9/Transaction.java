package com.example.practica9;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "AccesoADatosCollection")
public class Transaction {
    
    @Id
    private String id;
    private String description;
    private double quantity;
    private boolean income;
    private Date date;
    
    public Transaction() {
    }
    
    public Transaction(String id, String description, double quantity, boolean income, Date date) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.income = income;
        this.date = date;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getQuantity() {
        return quantity;
    }
    
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    
    public boolean isIncome() {
        return income;
    }
    
    public void setIncome(boolean income) {
        this.income = income;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", income=" + income +
                ", date=" + date +
                '}';
    }
}