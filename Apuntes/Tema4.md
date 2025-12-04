# Tema 4 Manejo de conectores

## Conector
Conjunto de clases y librerías que permiten unir la capa de aplicación con la capa de base de datos:
  - Conecta a la BBDD
  - Realiza consultas a la BBDD

---

## Desfase del objeto relacional
Entre:
- Aplicación <-> Base de datos física
- Objetos complejos <-> Datos simples

Este problema surge porque la programación orientada a objetos y las bases de datos relacionales tienen naturalezas distintas:
- La BBDD usa **datos simples**  
- La aplicación usa **objetos complejos**

Solución:
- Traducir objetos Java a tablas
- Crear entidades equivalentes en ambas capas

---

## Protocolos de acceso
En Java existen dos protocolos principales:

### **JDBC (Java Database Connectivity)**
- Solo Java  
- Multiplataforma  
- Recomendado por rendimiento  
- Orientado a objetos  

### **ODBC (Open Database Connectivity)**
- Usado por C, C++ y Java  
- Solo Windows  
- No recomendado en Java por pérdida de rendimiento  
- No orientado a objetos  

---

## Conexiones – Componentes
Los componentes principales de JDBC son:

- **API JDBC**  
  Librerías (java.sql, javax.sql) que permiten acceder a BBDD relacionales.

- **Paquete de pruebas JDBC**  
  Comprueba que los drivers cumplen el estándar.

- **Gestor JDBC (DriverManager)**  
  Conecta la app Java con el driver adecuado.  
  Modos:
    - Conexión directa  
    - Pool de conexiones

- **Puente JDBC–ODBC**  
  Permite usar drivers ODBC desde JDBC.

### Arquitecturas
- **Dos capas:** app y driver en la misma máquina  
- **Tres capas:** app → middleware → BBDD

---

## Tipos de drivers JDBC

### **Tipo 1 – JDBC–ODBC**
**Ventajas:**
- Fácil de encontrar  
- Acceso a drivers ODBC  

**Inconvenientes:**
- Bajo rendimiento  
- Funcionalidad limitada  
- Problemas con navegadores  

---

### **Tipo 2 – Driver nativo**
**Ventajas:**
- Buen rendimiento  

**Inconvenientes:**
- Requiere librerías del fabricante  
- No portable entre plataformas  
- No apto para aplicaciones en internet  

---

### **Tipo 3 – Middleware (tres capas)**
**Ventajas:**
- Muy portable  
- Buen rendimiento en internet  
- Escalable  

**Inconvenientes:**
- Requiere capa intermedia específica  

---

### **Tipo 4 – Protocolo nativo**
**Ventajas:**
- Muy buen rendimiento  
- Totalmente desarrollado en Java  
- No requiere software adicional  

**Inconvenientes:**
- Un driver distinto para cada tipo de BBDD  

---

## Configuración de una conexión en código

Pasos iniciales:
1. Descargar el driver `.jar` de la BBDD
2. Añadirlo al proyecto Java

### Registrar el driver:
```java
Class.forName(DRIVER);
````

### Establecer conexión
```java
Connection dbConnection =
    DriverManager.getConnection(URL_CONEXION, usuario, password);
```

### Crear Statement
```java
Statement statement = dbConnection.createStatement();
```

### Realizar consulta
```java
ResultSet rs = statement.executeQuery(consulta);
```

### Recorrer resultados
```java
while (rs.next()) {
    System.out.println(rs.getInt("ID"));
}
```
