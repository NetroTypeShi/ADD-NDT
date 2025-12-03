# ADD-NDT

## Tema 1 Manejo de Ficheros

### Introducción
Con java.io se pueden hacer operaciones básicas con ficheros como modificar, crear y borrarlos.

### Definición
Sucesión de bits almacenada en un fichero
Tipos:
- ASCII: Líneas de texto en formato ASCII
- Binarios: Info en código binario

#### Clase File (Java.io.File)
-File: Representación abstracta de un fichero
-Constructor: File(String pathname)
-Ejemplo:  
```java
File fichero = new File("/Desktop/file.txt");
```
- Método: [boolean]createNewFile()
- Crear Fichero: Crear un fichero nuevo y vacío con la  ruta indicada en el constructor
- Ejemplo:
```java
File fichero = new File("/Desktop/file.txt");
fichero.createNewFile
```
- Método: [boolean]delete()
- Borrar Fichero

- Método: [boolean]mkdir()
- Crea directorios y directorios padre
- Ejemplo: File fichero = new File("/Desktop/file.txt");
  fichero.mkdirs();
  
- Método: [String] getName()
- Devuelve el PathName
- Ejemplo: File fichero = new File("/Desktop/file.txt");
  fichero.getName();

- Método: [boolean] renameTo(File dest)
- Renombra el fichero y lo mueve
- Ejemplo:
  File fichero1 = new File("/Desktop/file.txt");
  File fichero2 = new File("/Desktop/file.txt");
  fichero1.renameTo(fichero2);

- Método: [boolean]exists()
-  Si existe o no
-  Ejemplo :
File fichero = new File("/Desktop/file.txt");
fichero.exists();

- Ruta absoluta: Ruta completa del recurso desde el directorio raíz
- Ruta relativa : Representa una parte de la ruta, se tiene en cuenta el directorio actual

- Método : [String] getPath()
- Devuelve la ruta relativa
- Ejemplo:
File fichero = new File("/Desktop/folder");
fichero.getPath();
fichero.getAbsolutePath();

- Metodo: [ File[]] listFiles();
- Devuelve un Array de Files que representan los ficheros del directorio
- Ejemplo:
  File fichero = new File("/Desktop/folder");
  fichero.listFiles();

- Método: [boolean] canWrite();
- [boolean] canRead();

### Ejercicio 1
```java
import java.io.File;

public class MyClass {
  public static void main(String args[]) {
      
    File directory = new File("/P1/cine_granada");
    directory.mkdir();
  }
}
```

### Ejercicio 2

```java
import java.io.File;

public class MyClass {
  public static void main(String args[]) {
      
    String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    for (String dia : dias) {
        File dir = new File("P1/"+dia);
        dir.mkdir();
  }
}
}
```

### Ejercicio 3
```java
import java.io.File;

public class MyClass {
  public static void main(String args[]) {
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        for (String dia : dias) {
        File origin = new File("P1/"+dia);
        File destination = new File("P1/cine_granada"+ dia);
        origin.renameTo(destination);
        
    }
 }
    
}
```

### Ejercicio 4
```java
import java.io.File;

public class MyClass {
  public static void main(String args[]) {
    
    String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    for (String dia : dias) {
        File dir = new File("P1/"+dia);
        if (!dir.exists()){
            dir.mkdir();
        }
  }
}
}
```

### Ejercicio 5
```java
import java.io.File;

public class MyClass {
  public static void main(String args[]) {
    
    String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    for (String dia : dias) {
        File dir = new File("P1/"+dia);
        if (!dir.exists()){
            dir.mkdir();
            System.out.println("Se ha creado el directorio con ruta absoluta:"+ dir.getAbsolutePath());
        }
   }
   
 }
}
```
### Ejercicio 6
```java
 public static void main(String args[]) {
    
    File cine = new File ("P1/cine_granada")
    String [] archivos = cine.list();
System.out.println("Archivos creados hasta ahora:")
if (archivos != null){
for (String.nombre
   }
   
 }
}

```

### Ejercicio 7
```java
import java.io.File;

public class MyClass {
  public static void main(String[] args) {
    
    String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    for (String dia : dias) {
        File archivo = new File("P1/"+dia+"/sesiones.txt");
        archivo.createNewFile();
   }
   
 }
}
```

### Formas de acceder a un fichero
- Secuencial: Acceder al fichero carácter a carácter de forma ordenada desde el inicio hasta el final
- Aleatorio: Permite acceder a posiciones concretas de nuestro dichero

#### Lectura
-  Clase: FileInputStream
-  Constructor: FileInputStream(String name/ File File)
- Leer datos en bruto

- Clase: [int]read()
- Lee un byte del fichero
- FileImputStream entrada = new
  FileImputStream("desktop/fichero.bin");
  entrada.read();
  entrada.close();

- Clase: write(byte[]b)
- Escribe un conjunto de bytes en un fichero
- FileOutputStream output = new
FileOutputStream("desktop/fichero.txt");
String cadena "prueba de escritura";
byte[] arrayBytes= cadena.getBytes();
output.write(arrayByte);
outout.close();

### Ejercicio 8
```java
import java.io.File;

public class MyClass {
  public static void main(String[] args) {
    
    String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    for (String dia : dias) {
        File dir = new File("P1/"+dia);
        if (!dir.exists()){
            dir.mkdir();
            System.out.println("Se ha creado el directorio con ruta absoluta:"+ dir.getAbsolutePath());
        }
        File archivo = new File("P1/"+dia+"/sesiones.txt");
        archivo.createNewFile();
        FileOutputStream output = new FileOutputStream("P1/Lunes/sesiones.txt");
        String cadena = "Spiderman (2002): 18:00 - 20:07";
        byte[] arrayBytes= cadena.getBytes();
        output.write(arrayByte);
        output.close();
   }
 }
}
```

### Ejercicio 9
```java
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MyClass {
  public static void main(String[] args) {
    
    String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    for (String dia : dias) {
        File dir = new File("P1/"+dia);
        if (!dir.exists()){
            dir.mkdir();
            System.out.println("Se ha creado el directorio con ruta absoluta:"+ dir.getAbsolutePath());
        }
        File archivo = new File("P1/"+dia+"/sesiones.txt");
        archivo.createNewFile();
        FileOutputStream output = new FileOutputStream(archivo);
        String cadena = "Spiderman (2002): 18:00 - 20:07";
        output.write(cadena.getBytes());
        output.close();
        
        FileInputStream input = new FileInputStream(archivo);
        
        int i;
        
        while((i = input.read()) != -1 ){
            System.out.print((char) i);
        }
   }
 }
}
```
- Clase: Random Access
- Constructor: RandomAccessFile(String path, string mode) / RandomAccessFile(File file, string mode)
- Modos de acceso:
r Modo lectura
rw Modo lectura y escritura
rwd Lectura y escritura síncrona (No garantiza actualización de metadatos)
rws Lectura y escritura síncrona (Garantiza actualización de metadatos)

- Clase: seek (long position)
- Permite posicionarnos en el punto que indiquemos en el fichero
- RandomAccessFile file = new RandomAccessFile(String path, string mode)
file.seek(12);
RandomAccessFile file = new RandomAccessFile(String path, string mode);
file.read( );
//Permite leer un Byte donde está colocado el puntero
file.readLine( );
//Permite leer la siguiente línea de Bytes a partir de donde está colocado el
puntero
RandomAccessFile file = new RandomAccessFile(String path, string mode);
file.write(“Example”.getBytes());
//Permite escribir un Byte donde está colocado el puntero
//El puntero avanza tras escribir
file.writeBytes(“Example”);
//Permite escribir un String como una secuencia de Bytes

### Ejercicio 10
```java
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.io.IOException;

public class MyClass {
    public static void main(String[] args) {
        File archivo = new File ("P1/cine_granada/Miércoles/sesiones.txt");
        
        RandomAccessFile raf = new RandomAccessFile (archivo, "rws");
        raf.writeBytes("Titanic (1998): 17:00 - 20:15");
        
        raf.seek("Titanic(".length());
        raf.writeBytes("1997");
        
        String linea;
        while ((linea = raf.readLine() != null)){
            System.outprintln(linea);
        }
        
    }
}

```
