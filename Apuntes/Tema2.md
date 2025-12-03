# Tema 2 Flujos

## Definición
- Secuencia ordenada de información
- Recurso de entrada
- Recurso de salida
- Unidireccionales

## Clases de flujos basados en ficheros
- Unidad: ficheros
- Bytes: FileInputStream
- Caracteres: FileReader

## Clases de flujos en tuberías
- Mecanismo que permite la comunicación entre dos hilos de un mismo programa
- Necesario pa comunicar un mismo proceso y dos hilos diferentes
- Unidad: Tuberías
- Bytes: PipedInputStream / PipedOutputStream
- Caracteres: PipedReader / PipedWriter

  ## Clases de Stream Arrays

  - Permiten leer y escribir datos en arrays de caracteres
  - Unidad: Arrays
  - ByteArrayInputStream
  - CharArrayWriter
 
  ## Clases de Stream Análisis

- Permiten analizar partes de flujos de datos
- Análisis
- PushBackInputStream / StreamTokenizer
- PushBackReader / LineNumberReader

### Ejercicio 1 ¿Modifica el archivo?
No, no lo modifica, ya que solo usa el filereader no el writer o append en ningún momento, si lo quisiera modificar sería así

```java
package com.mycompany.holaaa;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PushbackReader;
import java.io.FileReader;

public class Ejemplo {
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("tema2.txt");
            writer.write("Este es el nuevo contenido del archivo.\n");
            writer.close();
            System.out.println("Archivo modificado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            PushbackReader pushbackReader = new PushbackReader(new FileReader("tema2.txt"));
            int data = pushbackReader.read();
            System.out.println((char) data);
            pushbackReader.unread(data);
            data = pushbackReader.read();
            System.out.println((char) data);
            pushbackReader.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
```

### Ejercicio 2 ¿Puedo leer más de un caracter?
Pues si, con un bucle se pueden leer más de un caracter con cualquier método de tipo reader
```java
package com.mycompany.holaaa;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.FileReader;

public class Ejemplo {
    public static void main(String[] args) {
        try {
            PushbackReader pushbackReader = new PushbackReader(new FileReader("tema2.txt"));
            int data;
            while ((data = pushbackReader.read()) != -1) {
                System.out.print((char) data);
            }
            pushbackReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Ejercicio 2 ¿Puedo leer más de un caracter?
Pues si, con un bucle se pueden leer más de un caracter con cualquier método de tipo reader
```java
package com.mycompany.holaaa;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.FileReader;

public class Ejemplo {
    public static void main(String[] args) {
        try {
            PushbackReader pushbackReader = new PushbackReader(new FileReader("tema2.txt"));
            int data;
            while ((data = pushbackReader.read()) != -1) {
                System.out.print((char) data);
            }
            pushbackReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## Clases de análisis 2



