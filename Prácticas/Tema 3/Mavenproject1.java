/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject1;

import java.io.File;
import java.io.IOException;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Alumno
 */

public class Mavenproject1 {
    
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setIgnoringElementContentWhitespace(true);
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            File file = new File("ejemplo.xml");
            Document doc = builder.parse(file);
        } catch (ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        }
    }
}
