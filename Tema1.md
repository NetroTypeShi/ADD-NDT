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
- Ejemplo: File fichero = new File("/Desktop/file.txt");
  fichero.createNewFile
  
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

```

