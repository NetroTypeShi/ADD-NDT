package com.example.practica9;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "AccesoADatosCollection")
public class pokemon {
    
    @Id
    private String id;
    private String name;
    private String type;
    private int level;
    private String trainer;
    private Date captureDate;
    
    public pokemon() {
    }
    
    public pokemon(String id, String name, String type, int level, String trainer, Date captureDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.level = level;
        this.trainer = trainer;
        this.captureDate = captureDate;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public String getTrainer(){
        return trainer;
    }
    
    public void setTrainer(String trainer){
        this.trainer = trainer;
    }
    
    public Date getDate() {
        return captureDate;
    }
    
    public void setDate(Date captureDate) {
        this.captureDate = captureDate;
    }
    
    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", nombre='" + name + '\'' +
                ", tipo=" + type +
                ", nivel=" + level +
                ", entrenador="+ trainer +
                ", date=" + captureDate +
                '}';
    }
}