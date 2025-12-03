package com.mycompany.gestorproductosxml;

import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMManager {

    // Ruta del XML
    static String archivo = "productos.xml";

    // =========================================================
    // OPCIÓN 1 - Mostrar todos los productos
    // =========================================================
    public static void mostrarProductos() {
        try {
            // Cargar el XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(archivo));
            doc.getDocumentElement().normalize();

            // Obtener todos los nodos <producto>
            NodeList lista = doc.getElementsByTagName("producto");

            System.out.println("\n--- LISTA DE PRODUCTOS ---");
            for (int i = 0; i < lista.getLength(); i++) {
                Node nodo = lista.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nodo;
                    System.out.println("ID: " + e.getAttribute("id"));
                    System.out.println("Nombre: " + e.getElementsByTagName("nombre").item(0).getTextContent());
                    System.out.println("Categoría: " + e.getElementsByTagName("categoria").item(0).getTextContent());
                    System.out.println("Precio: " + e.getElementsByTagName("precio").item(0).getTextContent());
                    System.out.println("Stock: " + e.getElementsByTagName("stock").item(0).getTextContent());
                    System.out.println();
                }
            }

        } catch (Exception e) {
            System.out.println("Error al mostrar productos: " + e.getMessage());
        }
    }

    // =========================================================
    // OPCIÓN 2 - Agregar un nuevo producto
    // =========================================================
    public static void agregarProducto() {
        try {
            Scanner sc = new Scanner(System.in);

            // Cargar documento
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(archivo));
            doc.getDocumentElement().normalize();

            Element raiz = doc.getDocumentElement();

            // Pedir datos
            System.out.print("ID del producto: ");
            String id = sc.nextLine();
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Categoría: ");
            String categoria = sc.nextLine();
            System.out.print("Precio: ");
            String precio = sc.nextLine();
            System.out.print("Stock: ");
            String stock = sc.nextLine();

            // Crear nuevo elemento producto
            Element nuevo = doc.createElement("producto");
            nuevo.setAttribute("id", id);

            Element eNombre = doc.createElement("nombre");
            eNombre.setTextContent(nombre);
            nuevo.appendChild(eNombre);

            Element eCategoria = doc.createElement("categoria");
            eCategoria.setTextContent(categoria);
            nuevo.appendChild(eCategoria);

            Element ePrecio = doc.createElement("precio");
            ePrecio.setTextContent(precio);
            nuevo.appendChild(ePrecio);

            Element eStock = doc.createElement("stock");
            eStock.setTextContent(stock);
            nuevo.appendChild(eStock);

            // Agregar al XML
            raiz.appendChild(nuevo);

            // Guardar cambios
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(archivo));
            transformer.transform(source, result);

            System.out.println("Producto agregado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
        }
    }

    // =========================================================
    // OPCIÓN 3 - Incrementar precios por categoría
    // =========================================================
    public static void incrementarPreciosPorCategoria() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Ingrese la categoría: ");
            String categoria = sc.nextLine();

            // Cargar XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(archivo));
            doc.getDocumentElement().normalize();

            NodeList lista = doc.getElementsByTagName("producto");

            for (int i = 0; i < lista.getLength(); i++) {
                Node nodo = lista.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nodo;
                    String cat = e.getElementsByTagName("categoria").item(0).getTextContent();
                    if (cat.equalsIgnoreCase(categoria)) {
                        Element precioElem = (Element) e.getElementsByTagName("precio").item(0);
                        double precio = Double.parseDouble(precioElem.getTextContent());
                        precio = precio * 1.10; // aumenta 10%
                        precioElem.setTextContent(String.valueOf(precio));
                    }
                }
            }

            // Guardar en un nuevo archivo
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("productos_actualizados.xml"));
            transformer.transform(source, result);

            System.out.println("Precios actualizados y guardados en productos_actualizados.xml");

        } catch (Exception e) {
            System.out.println("Error al actualizar precios: " + e.getMessage());
        }
    }
}

