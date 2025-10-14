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


